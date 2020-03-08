package com.app.juawcevada.whatscookin.domain.recipe.repository

import com.app.juawcevada.whatscookin.common.data.Result
import com.app.juawcevada.whatscookin.domain.recipe.model.Recipe

interface RecipeRepository {
    suspend fun getRecipe(id: String): Result<Recipe>
}
