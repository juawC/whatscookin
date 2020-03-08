package com.app.juawcevada.whatscookin.common.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import arrow.core.Some
import com.app.juawcevada.whatscookin.common.util.Event
import javax.inject.Inject

class ViewStateStoreImpl<VS : ViewState, VE : ViewEffect, A : Action<VS, VE>>
@Inject constructor(defaultViewState: VS) : ViewStateStore<VS, VE, A> {

    private val _viewState = MutableLiveData<VS>().apply { value = defaultViewState }
    private val _viewEffect = MutableLiveData<Event<VE>>()

    override val viewState: LiveData<VS>
        get() = _viewState

    override val viewEffect: LiveData<Event<VE>>
        get() = _viewEffect

    override fun sendAction(action: A) {
        val newState = with(action) { _viewState.value!!.reduce() }
        val newEffect = action.produceEffect()

        if (newState is Some) {
            _viewState.value = newState.t
        }
        if (newEffect is Some) {
            _viewEffect.value = Event(newEffect.t)
        }
    }

    override fun getCurrentState(): VS {
        return viewState.value!!
    }
}