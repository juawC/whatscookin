package com.app.juawcevada.whatscookin.common.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.app.juawcevada.whatscookin.common.util.Event
import com.app.juawcevada.whatscookin.common.util.EventObserver

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, onDataChanged: (T) -> Unit) {
    liveData.observe(this, Observer { onDataChanged(it) })
}

fun <T> LifecycleOwner.observeEvent(liveData: LiveData<Event<T>>, onDataChanged: (T) -> Unit) {
    liveData.observe(this, EventObserver { onDataChanged(it) })
}
