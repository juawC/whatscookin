package com.app.juawcevada.whatscookin.util.factories.search

import com.app.juawcevada.whatscookin.domain.search.model.Ingredient

object IngredientFactory {
    fun create(
        aisle: String = "aisle",
        amount: Double = 0.0,
        id: String = "id",
        image: String = "image",
        name: String = "ingredient",
        original: String = "original",
        originalName: String = "original name",
        originalString: String = "original string",
        unit: String = "kg",
        unitLong: String = "unit long",
        unitShort: String = "unit short"

    ) = Ingredient(
        aisle,
        amount,
        id,
        image,
        name,
        original,
        originalName,
        originalString,
        unit,
        unitLong,
        unitShort
    )
}
