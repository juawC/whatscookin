package com.app.juawcevada.whatscookin.data.recipe.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeResponse(
    val id: Long,
    val title: String,
    val summary: String,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceUrl: String,
    val image: String = "",
    val extendedIngredients: List<ExtendedIngredientResponse>,
    val sourceName: String
)
