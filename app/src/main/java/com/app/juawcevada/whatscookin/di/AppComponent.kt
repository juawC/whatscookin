package com.app.juawcevada.whatscookin.di

import android.content.Context
import com.app.juawcevada.whatscookin.CookinApplication
import com.app.juawcevada.whatscookin.di.annotation.ActivityBuildersModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        ApiModule::class,
        ApiConfigsModule::class,
        RepositoriesModule::class,
        ActivityBuildersModule::class
    ]
)
interface AppComponent : AndroidInjector<CookinApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}