package com.app.juawcevada.whatscookin.ui

import com.app.juawcevada.whatscookin.ui.recipe.RecipeFragment
import com.app.juawcevada.whatscookin.ui.recipe.RecipeFragmentModule
import com.app.juawcevada.whatscookin.ui.search.RecipesByIngredientFragment
import com.app.juawcevada.whatscookin.ui.search.RecipesByIngredientFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [RecipesByIngredientFragmentModule::class])
    internal abstract fun contributeRecipesFragment(): RecipesByIngredientFragment

    @ContributesAndroidInjector(modules = [RecipeFragmentModule::class])
    internal abstract fun contributeRecipeFragment(): RecipeFragment
}
