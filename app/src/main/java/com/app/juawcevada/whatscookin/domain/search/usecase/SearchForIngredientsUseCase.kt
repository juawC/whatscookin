package com.app.juawcevada.whatscookin.domain.search.usecase

import com.app.juawcevada.whatscookin.common.data.Result
import com.app.juawcevada.whatscookin.domain.search.model.IngredientSearchItem
import com.app.juawcevada.whatscookin.domain.search.repository.SearchRepository
import javax.inject.Inject

class SearchForIngredientsUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(partialName: String): Result<List<IngredientSearchItem>> {
        return searchRepository.searchIngredients(partialName, 6)
    }
}