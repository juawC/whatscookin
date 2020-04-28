package com.app.juawcevada.whatscookin.ui.util

import com.app.juawcevada.whatscookin.common.data.Result
import com.app.juawcevada.whatscookin.common.util.Event

fun <T> T.toSuccess(): Result<T> {
    return Result.Success(this)
}

fun <T, E:Exception> E.toError(): Result<T> {
    return Result.Error(this)
}

fun <T> T.toEvent(): Event<T> {
    return Event(this)
}

inline val <reified T> Array<T>.tail: Array<T>
    get() = drop(1).toTypedArray()

val <T> Array<T>.head: T
    get() = first()