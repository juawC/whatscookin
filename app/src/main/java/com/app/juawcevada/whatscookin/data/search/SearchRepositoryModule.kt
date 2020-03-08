package com.app.juawcevada.whatscookin.data.search

import com.app.juawcevada.whatscookin.common.data.Mapper
import com.app.juawcevada.whatscookin.data.search.api.SearchService
import com.app.juawcevada.whatscookin.data.search.mapper.IngredientMapper
import com.app.juawcevada.whatscookin.data.search.mapper.IngredientSearchItemMapper
import com.app.juawcevada.whatscookin.data.search.mapper.RecipeSearchItemMapper
import com.app.juawcevada.whatscookin.data.search.model.IngredientResponse
import com.app.juawcevada.whatscookin.data.search.model.IngredientSearchItemResponse
import com.app.juawcevada.whatscookin.data.search.model.RecipeSearchItemResponse
import com.app.juawcevada.whatscookin.data.search.repository.SearchRepositoryImpl
import com.app.juawcevada.whatscookin.domain.search.model.Ingredient
import com.app.juawcevada.whatscookin.domain.search.model.IngredientSearchItem
import com.app.juawcevada.whatscookin.domain.search.model.RecipeSearchItem
import com.app.juawcevada.whatscookin.domain.search.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class SearchRepositoryModule {

    @Binds
    internal abstract fun searchRepository(mapper: SearchRepositoryImpl): SearchRepository

    @Binds
    internal abstract fun ingredientMapper(mapper: IngredientMapper): Mapper<IngredientResponse, Ingredient>

    @Binds
    internal abstract fun ingredientSearchItemMapper(mapper: IngredientSearchItemMapper): Mapper<IngredientSearchItemResponse, IngredientSearchItem>

    @Binds
    internal abstract fun recipeSearchItemMapper(mapper: RecipeSearchItemMapper): Mapper<RecipeSearchItemResponse, RecipeSearchItem>


    @Module
    companion object {
        @JvmStatic
        @Provides
        internal fun provideApi(
            retrofit: Retrofit
        ): SearchService {
            return retrofit.create(SearchService::class.java)
        }
    }
}
