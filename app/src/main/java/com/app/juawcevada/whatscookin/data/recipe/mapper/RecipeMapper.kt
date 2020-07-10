package com.app.juawcevada.whatscookin.data.recipe.mapper

import com.app.juawcevada.whatscookin.common.data.ListMapper
import com.app.juawcevada.whatscookin.common.data.Mapper
import com.app.juawcevada.whatscookin.data.recipe.model.ExtendedIngredientResponse
import com.app.juawcevada.whatscookin.data.recipe.model.RecipeResponse
import com.app.juawcevada.whatscookin.domain.recipe.model.ExtendedIngredient
import com.app.juawcevada.whatscookin.domain.recipe.model.Recipe
import javax.inject.Inject

class RecipeMapper @Inject constructor(
    private val extendedIngredientMapper: ListMapper<ExtendedIngredientResponse, ExtendedIngredient>
) : Mapper<RecipeResponse, Recipe> {

    override fun map(input: RecipeResponse): Recipe {
        return with(input) {
            Recipe(
                "$id",
                title,
                summary,
                readyInMinutes,
                servings,
                sourceUrl,
                image,
                extendedIngredientMapper.map(extendedIngredients),
                sourceName
            )
        }
    }
}
