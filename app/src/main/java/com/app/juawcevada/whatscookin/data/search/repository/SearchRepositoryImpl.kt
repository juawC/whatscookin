package com.app.juawcevada.whatscookin.data.search.repository

import com.app.juawcevada.whatscookin.common.data.ListMapper
import com.app.juawcevada.whatscookin.common.data.Mapper
import com.app.juawcevada.whatscookin.common.data.Result
import com.app.juawcevada.whatscookin.common.data.toResult
import com.app.juawcevada.whatscookin.data.search.api.SearchService
import com.app.juawcevada.whatscookin.data.search.model.IngredientSearchItemResponse
import com.app.juawcevada.whatscookin.data.search.model.RecipeSearchItemResponse
import com.app.juawcevada.whatscookin.domain.search.model.IngredientSearchItem
import com.app.juawcevada.whatscookin.domain.search.model.RecipeSearchItem
import com.app.juawcevada.whatscookin.domain.search.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchService: SearchService,
    recipeMapper: Mapper<RecipeSearchItemResponse, RecipeSearchItem>,
    ingredientSearchMapper: Mapper<IngredientSearchItemResponse, IngredientSearchItem>
) : SearchRepository {

    private val recipeListMapper = ListMapper(recipeMapper)
    private val ingredientSearchListMapper = ListMapper(ingredientSearchMapper)

    override suspend fun searchRecipesByIngredients(
        ingredients: List<String>,
        numberOfResults: Int
    ): Result<List<RecipeSearchItem>> {
        val searchResponse = searchService
            .searchByIngredients(
                ingredients.joinToString(separator = ",+"),
                numberOfResults
            )

        return try {
            searchResponse.toResult(recipeListMapper::map)
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }

    override suspend fun searchIngredients(
        partialName: String,
        numberOfResults: Int
    ): Result<List<IngredientSearchItem>> {
        val searchResponse = searchService.searchForIngredients(partialName, numberOfResults)

        return try {
            searchResponse.toResult(ingredientSearchListMapper::map)
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }
}
