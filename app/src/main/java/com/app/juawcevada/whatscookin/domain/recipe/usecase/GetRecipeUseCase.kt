package com.app.juawcevada.whatscookin.domain.recipe.usecase

import com.app.juawcevada.whatscookin.common.data.Result
import com.app.juawcevada.whatscookin.domain.recipe.model.Recipe
import com.app.juawcevada.whatscookin.domain.recipe.repository.RecipeRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetRecipeUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(id: String): Result<Recipe> {
        return recipeRepository.getRecipe(id)
    }
}
