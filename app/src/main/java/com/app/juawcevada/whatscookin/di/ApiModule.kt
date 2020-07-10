package com.app.juawcevada.whatscookin.di

import com.app.juawcevada.rickspace.di.annotation.ApiConfig
import com.app.juawcevada.whatscookin.common.data.ApiKeyInterceptor
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class ApiModule {

    @Provides
    @Reusable
    internal fun provideRetrofit(
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
    @Reusable
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
