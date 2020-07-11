package com.app.juawcevada.whatscookin.ui

import androidx.fragment.app.Fragment
import com.app.juawcevada.whatscookin.di.FragmentKey
import com.app.juawcevada.whatscookin.ui.recipe.RecipeFragment
import com.app.juawcevada.whatscookin.ui.search.RecipesByIngredientFragment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class MainActivityModule {

    @Binds
    @IntoMap
    @FragmentKey(RecipesByIngredientFragment::class)
    abstract fun bindSearchFragment(fragment: RecipesByIngredientFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(RecipeFragment::class)
    abstract fun bindDetailFragment(fragment: RecipeFragment): Fragment
}