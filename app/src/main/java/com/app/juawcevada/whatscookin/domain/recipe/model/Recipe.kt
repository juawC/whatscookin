package com.app.juawcevada.whatscookin.domain.recipe.model

data class Recipe(
    val id: String = "",
    val title: String = "",
    val summary: String = "",
    val readyInMinutes: Int = 0,
    val servings: Int = 0,
    val sourceUrl: String = "",
    val image: String = "",
    val extendedIngredients: List<ExtendedIngredient> = emptyList(),
    val sourceName: String = ""
)
