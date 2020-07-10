package com.app.juawcevada.whatscookin.common.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import arrow.core.Some
import com.app.juawcevada.whatscookin.common.util.Event
import javax.inject.Inject

class DefaultViewStateStore<VS : ViewState, VE : ViewEffect>
@Inject constructor(defaultViewState: VS) : ViewStateStore<VS, VE> {

    private val _viewState = MutableLiveData<VS>().apply { value = defaultViewState }
    private val _viewEffect = MutableLiveData<Event<VE>>()

    override val viewState: LiveData<VS>
        get() = _viewState

    override val viewEffect: LiveData<Event<VE>>
        get() = _viewEffect

    val currentState: VS
        get() = viewState.value!!

    fun sendEffect(viewEffect: VE) {
        _viewEffect.value = Event(viewEffect)
    }

    fun updateState(viewStateUpdate: VS.() -> VS) {
        _viewState.value = currentState.viewStateUpdate()
    }
}