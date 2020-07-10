package com.app.juawcevada.whatscookin.di

import com.app.juawcevada.whatscookin.di.annotation.ApiConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class ApiConfigsModule {

    @Provides
    @ApiConfig
    fun providesApiUrl(): String {
        return "https://api.spoonacular.com/"
    }
}