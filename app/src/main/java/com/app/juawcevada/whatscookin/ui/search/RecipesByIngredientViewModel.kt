package com.app.juawcevada.whatscookin.ui.search

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Option
import arrow.core.toOption
import com.app.juawcevada.whatscookin.R
import com.app.juawcevada.whatscookin.common.data.Result
import com.app.juawcevada.whatscookin.common.ui.LceViewState
import com.app.juawcevada.whatscookin.common.ui.ViewStateStore
import com.app.juawcevada.whatscookin.common.ui.ViewStateStoreImpl
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
    private val store: ViewStateStoreImpl<ViewState, ViewEffect, Action> = ViewStateStoreImpl(
        ViewState()
    )
) : ViewModel(),
    ViewStateStore<RecipesByIngredientViewModel.ViewState, RecipesByIngredientViewModel.ViewEffect, RecipesByIngredientViewModel.Action> by store,
    RecipesByIngredientsViewActions {

    override fun searchRecipes() {
        viewModelScope.launch {
            sendAction(Action.LoadRecipes)

            val ingredients: List<String> = getCurrentState().ingredients
            val recipesResult = searchForRecipesByIngredientsUseCase(ingredients)
            store.sendAction(recipesResult.fold(Action::Error, Action::RecipesLoaded))
        }
    }

    override fun retry() {
        searchRecipes()
    }

    override fun openRecipe(recipe: RecipeSearchItem) {
        sendAction(Action.OpenDetail(recipe))
    }

    override fun addIngredient(ingredient: String) {
        sendAction(Action.AddIngredient(ingredient))
        searchRecipes()
    }

    override fun removeIngredient(ingredient: String) {
        sendAction(Action.RemoveIngredient(ingredient))
        searchRecipes()
    }

    override fun autoCompleteIngredient(partialIngredient: String) {
        viewModelScope.launch {
            when (val recipesResult = searchForIngredientsUseCase(partialIngredient)) {
                is Result.Success -> sendAction(Action.IngredientsLoaded(recipesResult.data))
            }
        }
    }

    sealed class ViewEffect : com.app.juawcevada.whatscookin.common.ui.ViewEffect {
        data class NavigateToDetail(val recipe: RecipeSearchItem) : ViewEffect()
        data class IngredientsAutoComplete(val ingredients: List<IngredientSearchItem>) :
            ViewEffect()
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

    sealed class Action : com.app.juawcevada.whatscookin.common.ui.Action<ViewState, ViewEffect> {
        object LoadRecipes : Action() {
            override fun ViewState.reduce(): Option<ViewState> {
                return copy(
                    isLoading = recipes.isEmpty(),
                    isRefreshing = recipes.isNotEmpty()
                ).toOption()
            }
        }

        data class RemoveIngredient(private val ingredient: String) : Action() {
            override fun ViewState.reduce(): Option<ViewState> {
                return copy(ingredients = ingredients - ingredient).toOption()
            }
        }

        data class AddIngredient(private val ingredient: String) : Action() {
            override fun ViewState.reduce(): Option<ViewState> {
                return copy(ingredients = ingredients + ingredient).toOption()
            }
        }

        data class RecipesLoaded(private val recipesList: List<RecipeSearchItem>) : Action() {
            override fun ViewState.reduce(): Option<ViewState> {
                return copy(
                    isLoading = false,
                    isRefreshing = false,
                    recipes = recipesList
                ).toOption()
            }
        }

        data class IngredientsLoaded(private val ingredients: List<IngredientSearchItem>) :
            Action() {
            override fun produceEffect() =
                ViewEffect.IngredientsAutoComplete(ingredients).toOption()
        }

        data class Error(private val error: Throwable) : Action() {
            override fun ViewState.reduce(): Option<ViewState> {
                return copy(isLoading = false, errorMessage = R.string.error_message).toOption()
            }
        }

        data class OpenDetail(val recipe: RecipeSearchItem) : Action() {
            override fun produceEffect() = ViewEffect.NavigateToDetail(recipe).toOption()
        }
    }
}
