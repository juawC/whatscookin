package com.app.juawcevada.whatscookin.util

import androidx.lifecycle.ViewModel
import com.app.juawcevada.whatscookin.di.ViewModelFactory
import dagger.internal.InstanceFactory

/**
 * Creates a one off view model factory for the given view model instance.
 */
/**
 * Creates a one off view model factory for the given view model instance.
 */
fun <T : ViewModel> T.createTestFactory(): ViewModelFactory<T> {
    return ViewModelFactory<T>(InstanceFactory.create(this) as InstanceFactory)
}
