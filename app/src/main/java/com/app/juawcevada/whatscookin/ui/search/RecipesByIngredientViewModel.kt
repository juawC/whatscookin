package com.app.juawcevada.whatscookin.ui.search

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.juawcevada.whatscookin.R
import com.app.juawcevada.whatscookin.common.ui.DefaultViewStateStore
import com.app.juawcevada.whatscookin.common.ui.LceViewState
import com.app.juawcevada.whatscookin.common.ui.ViewStateStore
import com.app.juawcevada.whatscookin.domain.search.model.IngredientSearchItem
import com.app.juawcevada.whatscookin.domain.search.model.RecipeSearchItem
import com.app.juawcevada.whatscookin.domain.search.usecase.SearchForIngredientsUseCase
import com.app.juawcevada.whatscookin.domain.search.usecase.SearchForRecipesByIngredientsUseCase
import com.app.juawcevada.whatscookin.testing.OpenForTesting
import kotlinx.coroutines.launch
import javax.inject.Inject

@OpenForTesting
class RecipesByIngredientViewModel @Inject constructor(
    private val searchForRecipesByIngredientsUseCase: SearchForRecipesByIngredientsUseCase,
    private val searchForIngredientsUseCase: SearchForIngredientsUseCase,
    private val viewStateStore: DefaultViewStateStore<ViewState, ViewEffect> = DefaultViewStateStore(
        ViewState()
    )
) : ViewModel(),
    ViewStateStore<RecipesByIngredientViewModel.ViewState, RecipesByIngredientViewModel.ViewEffect> by viewStateStore,
    RecipesByIngredientsViewActions {

    override fun searchRecipes() {
        updateRecipesList()
    }

    override fun retry() {
        updateRecipesList()
    }

    override fun openRecipe(recipe: RecipeSearchItem) {
        viewStateStore.sendNavigateToDetailEvent(recipe)
    }

    override fun addIngredient(ingredientName: String) {
        viewStateStore.displayNewIngredient(ingredientName)
        updateRecipesList()
    }

    override fun removeIngredient(ingredientName: String) {
        viewStateStore.removeExistingIngredient(ingredientName)
        updateRecipesList()
    }

    override fun autoCompleteIngredient(partialIngredient: String) {
        viewModelScope.launch {
            searchForIngredientsUseCase(partialIngredient).fold(
                { /* Do nothing on error */ },
                { viewStateStore.displayAutoCompleteList(it) }
            )
        }
    }

    private fun updateRecipesList() {
        viewModelScope.launch {
            viewStateStore.displayLoading()

            searchForRecipesByIngredientsUseCase(
                viewStateStore.currentState.ingredients
            ).fold(
                { viewStateStore.displayError(it) },
                { viewStateStore.displayRecipes(it) }
            )
        }
    }

    private fun DefaultViewStateStore<ViewState, ViewEffect>.displayLoading() {
        updateState {
            copy(
                isLoading = recipes.isEmpty(),
                isRefreshing = recipes.isNotEmpty()
            )
        }
    }

    private fun DefaultViewStateStore<ViewState, ViewEffect>.displayError(error: Throwable) {
        updateState {
            copy(
                isLoading = false,
                errorMessage = R.string.error_message
            )
        }
    }

    private fun DefaultViewStateStore<ViewState, ViewEffect>.displayRecipes(
        recipesList: List<RecipeSearchItem>
    ) {
        updateState {
            copy(
                isLoading = false,
                isRefreshing = false,
                recipes = recipesList
            )
        }
    }

    private fun DefaultViewStateStore<ViewState, ViewEffect>.displayNewIngredient(
        ingredient: String
    ) {
        updateState {
            copy(ingredients = ingredients + ingredient)
        }
    }

    private fun DefaultViewStateStore<ViewState, ViewEffect>.removeExistingIngredient(
        ingredient: String
    ) {
        updateState {
            copy(ingredients = ingredients - ingredient)
        }
    }

    private fun DefaultViewStateStore<ViewState, ViewEffect>.sendNavigateToDetailEvent(
        recipe: RecipeSearchItem
    ) {
        sendEffect(
            ViewEffect.NavigateToDetail(recipe)
        )
    }

    private fun DefaultViewStateStore<ViewState, ViewEffect>.displayAutoCompleteList(
        ingredients: List<IngredientSearchItem>
    ) {
        sendEffect(
            ViewEffect.IngredientsAutoCompleteUpdate(ingredients)
        )
    }

    sealed class ViewEffect : com.app.juawcevada.whatscookin.common.ui.ViewEffect {
        data class NavigateToDetail(val recipe: RecipeSearchItem) : ViewEffect()
        data class IngredientsAutoCompleteUpdate(
            val ingredients: List<IngredientSearchItem>
        ) : ViewEffect()
    }

    data class ViewState(
        override val isLoading: Boolean = false,
        val isRefreshing: Boolean = false,
        val ingredients: List<String> = emptyList(),
        val recipes: List<RecipeSearchItem> = emptyList(),
        @StringRes override val errorMessage: Int? = null
    ) : LceViewState {

        @Inject
        constructor() : this(recipes = emptyList())

        val isEmptyView: Boolean
            get() = recipes.isEmpty() && !isLoading
    }
}
