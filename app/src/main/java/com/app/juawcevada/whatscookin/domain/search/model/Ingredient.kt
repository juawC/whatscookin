package com.app.juawcevada.whatscookin.domain.search.model

data class Ingredient(
    val aisle: String,
    val amount: Double,
    val id: String,
    val image: String,
    val name: String,
    val original: String,
    val originalName: String,
    val originalString: String,
    val unit: String,
    val unitLong: String,
    val unitShort: String
)