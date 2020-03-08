package com.app.juawcevada.whatscookin.di.annotation

import com.app.juawcevada.whatscookin.ui.MainActivity
import com.app.juawcevada.whatscookin.ui.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun bindMainActivity(): MainActivity
}