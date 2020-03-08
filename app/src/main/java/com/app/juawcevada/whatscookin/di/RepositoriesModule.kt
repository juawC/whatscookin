package com.app.juawcevada.whatscookin.di

import com.app.juawcevada.whatscookin.data.recipe.RecipeRepositoryModule
import com.app.juawcevada.whatscookin.data.search.SearchRepositoryModule
import dagger.Module

@Module(includes = [SearchRepositoryModule::class, RecipeRepositoryModule::class])
class RepositoriesModule
