package com.app.juawcevada.whatscookin.data.search.mapper

import com.app.juawcevada.whatscookin.common.data.Mapper
import com.app.juawcevada.whatscookin.data.search.model.IngredientResponse
import com.app.juawcevada.whatscookin.domain.search.model.Ingredient
import javax.inject.Inject

class IngredientMapper @Inject constructor() : Mapper<IngredientResponse, Ingredient> {
    override fun map(input: IngredientResponse): Ingredient {
        return with(input) {
            Ingredient(
                aisle,
                amount,
                "$id",
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
    }
}
