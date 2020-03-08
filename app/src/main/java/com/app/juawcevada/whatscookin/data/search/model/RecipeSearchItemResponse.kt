package com.app.juawcevada.whatscookin.data.search.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeSearchItemResponse(
    val id: Long,
    val image: String,
    val imageType: String,
    val likes: Int,
    val missedIngredientCount: Int,
    val missedIngredients: List<IngredientResponse>,
    val title: String,
    val unusedIngredients: List<IngredientResponse>,
    val usedIngredientCount: Int,
    val usedIngredients: List<IngredientResponse>
)
