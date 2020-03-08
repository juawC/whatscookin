package com.app.juawcevada.whatscookin.data.search.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IngredientResponse(
    val aisle: String,
    val amount: Double,
    val id: Long,
    val image: String,
    val name: String,
    val original: String,
    val originalName: String,
    val originalString: String,
    val unit: String,
    val unitLong: String,
    val unitShort: String
)