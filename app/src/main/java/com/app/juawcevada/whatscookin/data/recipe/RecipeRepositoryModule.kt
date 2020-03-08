package com.app.juawcevada.whatscookin.data.recipe

import com.app.juawcevada.whatscookin.common.data.Mapper
import com.app.juawcevada.whatscookin.data.recipe.api.RecipeService
import com.app.juawcevada.whatscookin.data.recipe.mapper.ExtendedIngredientMapper
import com.app.juawcevada.whatscookin.data.recipe.mapper.RecipeMapper
import com.app.juawcevada.whatscookin.data.recipe.model.ExtendedIngredientResponse
import com.app.juawcevada.whatscookin.data.recipe.model.RecipeResponse
import com.app.juawcevada.whatscookin.data.recipe.repository.RecipeRepositoryImpl
import com.app.juawcevada.whatscookin.domain.recipe.model.ExtendedIngredient
import com.app.juawcevada.whatscookin.domain.recipe.model.Recipe
import com.app.juawcevada.whatscookin.domain.recipe.repository.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class RecipeRepositoryModule {

    @Binds
    internal abstract fun recipeRepository(mapper: RecipeRepositoryImpl): RecipeRepository

    @Binds
    internal abstract fun ingredientMapper(mapper: ExtendedIngredientMapper): Mapper<ExtendedIngredientResponse, ExtendedIngredient>

    @Binds
    internal abstract fun recipeMapper(mapper: RecipeMapper): Mapper<RecipeResponse, Recipe>

    @Module
    companion object {
        @JvmStatic
        @Provides
        internal fun provideApi(
            retrofit: Retrofit
        ): RecipeService {
            return retrofit.create(RecipeService::class.java)
        }
    }
}
