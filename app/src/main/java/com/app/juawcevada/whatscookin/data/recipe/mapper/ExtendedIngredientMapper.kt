package com.app.juawcevada.whatscookin.data.recipe.mapper

import com.app.juawcevada.whatscookin.common.data.Mapper
import com.app.juawcevada.whatscookin.data.recipe.model.ExtendedIngredientResponse
import com.app.juawcevada.whatscookin.domain.recipe.model.ExtendedIngredient
import javax.inject.Inject

class ExtendedIngredientMapper @Inject constructor() :
    Mapper<ExtendedIngredientResponse, ExtendedIngredient> {
    override fun map(input: ExtendedIngredientResponse): ExtendedIngredient {
        return with(input) {
            ExtendedIngredient(
                "$id",
                amount,
                aisle,
                name,
                unit
            )
        }
    }
}
