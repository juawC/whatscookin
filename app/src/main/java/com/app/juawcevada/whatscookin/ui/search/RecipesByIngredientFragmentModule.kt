package com.app.juawcevada.whatscookin.ui.search

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module

@Module
abstract class RecipesByIngredientFragmentModule {

    @Binds
    internal abstract fun bindFragment(fragment: RecipesByIngredientFragment): Fragment
}