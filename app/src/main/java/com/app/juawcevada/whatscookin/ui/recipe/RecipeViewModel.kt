package com.app.juawcevada.whatscookin.ui.recipe

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.juawcevada.whatscookin.R
import com.app.juawcevada.whatscookin.common.ui.DefaultViewStateStore
import com.app.juawcevada.whatscookin.common.ui.LceViewState
import com.app.juawcevada.whatscookin.common.ui.ViewStateStore
import com.app.juawcevada.whatscookin.domain.recipe.model.Recipe
import com.app.juawcevada.whatscookin.domain.recipe.usecase.GetRecipeUseCase
import com.app.juawcevada.whatscookin.testing.OpenForTesting
import kotlinx.coroutines.launch
import javax.inject.Inject

@OpenForTesting
class RecipeViewModel @Inject constructor(
    val recipeId: String,
    private val getRecipeUseCase: GetRecipeUseCase,
    private val viewStateStore: DefaultViewStateStore<ViewState, ViewEffect> = DefaultViewStateStore(
        ViewState()
    )
) : ViewModel(),
    ViewStateStore<RecipeViewModel.ViewState, RecipeViewModel.ViewEffect> by viewStateStore,
    RecipeViewActions {

    init {
        loadRecipe()
    }

    override fun retry() {
        loadRecipe()
    }

    private fun loadRecipe() {
        viewModelScope.launch {
            viewStateStore.displayLoading()

            getRecipeUseCase(
                recipeId
            ).fold(
                { viewStateStore.displayError(it) },
                { viewStateStore.displayRecipe(it) }
            )
        }
    }

    override fun openSource(url: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun DefaultViewStateStore<ViewState, ViewEffect>.displayRecipe(
        recipeLoaded: Recipe
    ) {
        updateState {
            copy(
                isLoading = false,
                recipe = recipeLoaded
            )
        }
    }

    private fun DefaultViewStateStore<ViewState, ViewEffect>.displayError(
        error: Throwable
    ) {
        updateState {
            copy(
                isLoading = false,
                errorMessage = R.string.error_message
            )
        }
    }

    private fun DefaultViewStateStore<ViewState, ViewEffect>.displayLoading() {
        updateState {
            copy(
                isLoading = true
            )
        }
    }

    sealed class ViewEffect : com.app.juawcevada.whatscookin.common.ui.ViewEffect {
        data class OpenSource(val url: String) : ViewEffect()
    }

    data class ViewState(
        override val isLoading: Boolean = false,
        val recipe: Recipe = Recipe(),
        @StringRes override val errorMessage: Int? = null
    ) : LceViewState {

        @Inject
        constructor() : this(recipe = Recipe())
    }
}
