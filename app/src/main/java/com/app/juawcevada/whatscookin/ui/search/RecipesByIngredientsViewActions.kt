package com.app.juawcevada.whatscookin.ui.search

import com.app.juawcevada.whatscookin.common.ui.LceViewActions
import com.app.juawcevada.whatscookin.domain.search.model.RecipeSearchItem

interface RecipesByIngredientsViewActions : LceViewActions {
    fun openRecipe(recipe: RecipeSearchItem)
    fun addIngredient(ingredient: String)
    fun removeIngredient(ingredient: String)
    fun autoCompleteIngredient(partialIngredient: String)
    fun searchRecipes()
}