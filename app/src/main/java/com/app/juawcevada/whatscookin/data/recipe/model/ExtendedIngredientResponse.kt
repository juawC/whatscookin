package com.app.juawcevada.whatscookin.data.recipe.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExtendedIngredientResponse(
    val id: Long,
    val amount: Float,
    val aisle: String,
    val name: String,
    val unit: String
)
