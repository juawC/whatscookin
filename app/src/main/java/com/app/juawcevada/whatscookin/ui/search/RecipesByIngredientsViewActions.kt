package com.app.juawcevada.whatscookin.ui.search

import com.app.juawcevada.whatscookin.common.ui.LceViewActions
import com.app.juawcevada.whatscookin.domain.search.model.RecipeSearchItem

interface RecipesByIngredientsViewActions : LceViewActions {
    fun openRecipe(recipe: RecipeSearchItem)
    fun addIngredient(ingredientName: String)
    fun removeIngredient(ingredientName: String)
    fun autoCompleteIngredient(partialIngredient: String)
    fun searchRecipes()
}