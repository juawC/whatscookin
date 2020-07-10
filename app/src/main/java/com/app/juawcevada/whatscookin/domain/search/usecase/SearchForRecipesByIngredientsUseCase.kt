package com.app.juawcevada.whatscookin.domain.search.usecase

import com.app.juawcevada.whatscookin.common.data.Result
import com.app.juawcevada.whatscookin.domain.search.model.RecipeSearchItem
import com.app.juawcevada.whatscookin.domain.search.repository.SearchRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SearchForRecipesByIngredientsUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(ingredients: List<String>): Result<List<RecipeSearchItem>> {
        return searchRepository.searchRecipesByIngredients(ingredients, 20)
    }
}