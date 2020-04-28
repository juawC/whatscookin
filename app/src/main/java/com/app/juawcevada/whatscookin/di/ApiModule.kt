package com.app.juawcevada.whatscookin.di

import com.app.juawcevada.rickspace.di.annotation.ApiConfig
import com.app.juawcevada.whatscookin.common.data.ApiKeyInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    internal fun provideApi(
        @ApiConfig url: String,
        okHttpClient: OkHttpClient
    ): Retrofit {

        return Retrofit.Builder()
            .callFactory(okHttpClient)
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(ApiKeyInterceptor())
            .build()
    }
}
