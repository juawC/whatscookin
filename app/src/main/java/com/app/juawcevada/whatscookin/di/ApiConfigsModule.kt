package com.app.juawcevada.whatscookin.di

import com.app.juawcevada.rickspace.di.annotation.ApiConfig
import dagger.Module
import dagger.Provides


@Module
class ApiConfigsModule {

    @Provides
    @ApiConfig
    fun providesApiUrl(): String {
        return "https://api.spoonacular.com/"
    }
}