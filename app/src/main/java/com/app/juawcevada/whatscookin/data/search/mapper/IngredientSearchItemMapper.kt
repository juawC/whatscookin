package com.app.juawcevada.whatscookin.data.search.mapper

import com.app.juawcevada.whatscookin.common.data.Mapper
import com.app.juawcevada.whatscookin.data.search.model.IngredientSearchItemResponse
import com.app.juawcevada.whatscookin.domain.search.model.IngredientSearchItem
import javax.inject.Inject

class IngredientSearchItemMapper @Inject constructor() :
    Mapper<IngredientSearchItemResponse, IngredientSearchItem> {
    override fun map(input: IngredientSearchItemResponse): IngredientSearchItem {
        return with(input) {
            IngredientSearchItem(
                name,
                image
            )
        }
    }
}
