package com.app.juawcevada.whatscookin.data.recipe.repository

import com.app.juawcevada.whatscookin.common.data.Mapper
import com.app.juawcevada.whatscookin.common.data.Result
import com.app.juawcevada.whatscookin.common.data.toResult
import com.app.juawcevada.whatscookin.data.recipe.api.RecipeService
import com.app.juawcevada.whatscookin.data.recipe.model.RecipeResponse
import com.app.juawcevada.whatscookin.domain.recipe.model.Recipe
import com.app.juawcevada.whatscookin.domain.recipe.repository.RecipeRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class RecipeRepositoryImpl @Inject constructor(
    private val recipeService: RecipeService,
    private val recipeMapper: Mapper<RecipeResponse, Recipe>
) : RecipeRepository {

    override suspend fun getRecipe(id: String): Result<Recipe> {
        return try {
            recipeService.getRecipe(id).toResult(recipeMapper::map)
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }
}
