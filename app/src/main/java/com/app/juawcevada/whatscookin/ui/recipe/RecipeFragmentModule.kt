package com.app.juawcevada.whatscookin.ui.recipe

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RecipeFragmentModule {

    @Binds
    internal abstract fun bindFragment(fragment: RecipeFragment): Fragment

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun providesRecipeId(fragment: RecipeFragment): String {
            return RecipeFragmentArgs.fromBundle(fragment.arguments!!).id
        }
    }
}
