package com.app.juawcevada.whatscookin.util.factories.search

import com.app.juawcevada.whatscookin.domain.search.model.Ingredient
import com.app.juawcevada.whatscookin.domain.search.model.RecipeSearchItem

object RecipeSearchItemFactory {
    fun create(
        id: String = "id",
        image: String = "image",
        imageType: String = "png",
        likes: Int = 0,
        missedIngredientCount: Int = 0,
        missedIngredients: List<Ingredient> = emptyList(),
        title: String = "Awesome food",
        unusedIngredients: List<Ingredient> = emptyList(),
        usedIngredientCount: Int = 0,
        usedIngredients: List<Ingredient> = emptyList()

    ) = RecipeSearchItem(
        id,
        image,
        imageType,
        likes,
        missedIngredientCount,
        missedIngredients,
        title,
        unusedIngredients,
        usedIngredientCount,
        usedIngredients
    )
}