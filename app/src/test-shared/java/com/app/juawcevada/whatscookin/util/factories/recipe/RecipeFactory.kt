package com.app.juawcevada.whatscookin.util.factories.recipe

import com.app.juawcevada.whatscookin.domain.recipe.model.ExtendedIngredient
import com.app.juawcevada.whatscookin.domain.recipe.model.Recipe

object RecipeFactory {
    fun create(
        id: String = "id",
        title: String = "title",
        summary: String = "summary",
        readyInMinutes: Int = 1,
        servings: Int = 0,
        sourceUrl: String = "",
        image: String = "",
        extendedIngredients: List<ExtendedIngredient> = emptyList(),
        sourceName: String = ""
    ) = Recipe(
        id,
        title,
        summary,
        readyInMinutes,
        servings,
        sourceUrl,
        image,
        extendedIngredients,
        sourceName
    )
}
