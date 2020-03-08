package com.app.juawcevada.whatscookin.data.search.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IngredientSearchItemResponse(
    val name: String,
    val image: String
)