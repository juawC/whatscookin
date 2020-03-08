package com.app.juawcevada.whatscookin.ui.recipe

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Option
import arrow.core.toOption
import com.app.juawcevada.whatscookin.R
import com.app.juawcevada.whatscookin.common.ui.LceViewState
import com.app.juawcevada.whatscookin.common.ui.ViewStateStore
import com.app.juawcevada.whatscookin.common.ui.ViewStateStoreImpl
import com.app.juawcevada.whatscookin.domain.recipe.model.Recipe
import com.app.juawcevada.whatscookin.domain.recipe.usecase.GetRecipeUseCase
import com.app.juawcevada.whatscookin.testing.OpenForTesting
import kotlinx.coroutines.launch
import javax.inject.Inject

@OpenForTesting
class RecipeViewModel @Inject constructor(
    val recipeId: String,
    private val getRecipeUseCase: GetRecipeUseCase,
    private val store: ViewStateStoreImpl<ViewState, ViewEffect, Action> = ViewStateStoreImpl(
        ViewState()
    )
) : ViewModel(),
    ViewStateStore<RecipeViewModel.ViewState, RecipeViewModel.ViewEffect, RecipeViewModel.Action> by store,
    RecipeViewActions {

    init {
        loadRecipe()
    }

    override fun retry() {
        loadRecipe()
    }

    private fun loadRecipe() {
        viewModelScope.launch {
            store.sendAction(Action.LoadRecipe)
            store.sendAction(getRecipeUseCase(recipeId).fold(Action::Error, Action::RecipeLoaded))
        }
    }

    override fun openSource(url: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

    sealed class Action : com.app.juawcevada.whatscookin.common.ui.Action<ViewState, ViewEffect> {
        object LoadRecipe : Action() {
            override fun ViewState.reduce(): Option<ViewState> {
                return copy(
                    isLoading = true
                ).toOption()
            }
        }

        data class RecipeLoaded(private val recipeLoaded: Recipe) : Action() {
            override fun ViewState.reduce(): Option<ViewState> {
                return copy(
                    isLoading = false,
                    recipe = recipeLoaded
                ).toOption()
            }
        }

        data class Error(private val error: Throwable) : Action() {
            override fun ViewState.reduce(): Option<ViewState> {
                return copy(isLoading = false, errorMessage = R.string.error_message).toOption()
            }
        }
    }
}
