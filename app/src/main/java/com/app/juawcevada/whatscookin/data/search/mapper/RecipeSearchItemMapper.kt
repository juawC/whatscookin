package com.app.juawcevada.whatscookin.data.search.mapper

import com.app.juawcevada.whatscookin.common.data.ListMapper
import com.app.juawcevada.whatscookin.common.data.Mapper
import com.app.juawcevada.whatscookin.data.search.model.IngredientResponse
import com.app.juawcevada.whatscookin.data.search.model.RecipeSearchItemResponse
import com.app.juawcevada.whatscookin.domain.search.model.Ingredient
import com.app.juawcevada.whatscookin.domain.search.model.RecipeSearchItem
import javax.inject.Inject

class RecipeSearchItemMapper @Inject constructor(
    ingredientMapper: Mapper<IngredientResponse, Ingredient>
) : Mapper<RecipeSearchItemResponse, RecipeSearchItem> {
    private val ingredientListMapper = ListMapper(ingredientMapper)

    override fun map(input: RecipeSearchItemResponse): RecipeSearchItem {
        return with(input) {
            RecipeSearchItem(
                "$id",
                image,
                imageType,
                likes,
                missedIngredientCount,
                ingredientListMapper.map(missedIngredients),
                title,
                ingredientListMapper.map(unusedIngredients),
                usedIngredientCount,
                ingredientListMapper.map(usedIngredients)
            )
        }
    }
}
