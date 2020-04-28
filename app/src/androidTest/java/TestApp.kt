package com.app.juawcevada.whatscookin

import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector

class TestApp : android.app.Application(), HasAndroidInjector {
    override fun androidInjector(): AndroidInjector<Any> {
        return AndroidInjector { }
    }
}
