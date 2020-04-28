package com.app.juawcevada.whatscookin.domain.search.repository

import com.app.juawcevada.whatscookin.common.data.Result
import com.app.juawcevada.whatscookin.domain.search.model.IngredientSearchItem
import com.app.juawcevada.whatscookin.domain.search.model.RecipeSearchItem

interface SearchRepository {
    suspend fun searchRecipesByIngredients(
        ingredients: List<String>,
        numberOfResults: Int
    ): Result<List<RecipeSearchItem>>

    suspend fun searchIngredients(
        partialName: String,
        numberOfResults: Int
    ): Result<List<IngredientSearchItem>>
}
