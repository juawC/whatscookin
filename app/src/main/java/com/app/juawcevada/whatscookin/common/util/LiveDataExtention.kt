package com.app.juawcevada.whatscookin.common.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

fun <R, T> LiveData<T>.map(mapFunction: (T) -> R): LiveData<R> {
    return Transformations.map(this, mapFunction)
}
