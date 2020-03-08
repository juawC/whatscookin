package com.app.juawcevada.whatscookin.domain.recipe.model

data class ExtendedIngredient(
    val id: String,
    val amount: Float,
    val aisle: String,
    val name: String,
    val unit: String
)
