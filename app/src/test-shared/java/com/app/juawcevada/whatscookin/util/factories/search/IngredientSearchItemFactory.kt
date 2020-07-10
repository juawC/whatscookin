package com.app.juawcevada.whatscookin.util.factories.search

import com.app.juawcevada.whatscookin.domain.search.model.IngredientSearchItem

object IngredientSearchItemFactory {

    fun create(
        name: String = "Chicken",
        image: String = "chicken.png"
    ) = IngredientSearchItem(name, image)
}
