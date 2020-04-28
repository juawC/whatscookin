package com.app.juawcevada.whatscookin.common.ui

import androidx.lifecycle.LiveData
import com.app.juawcevada.whatscookin.common.util.Event

interface ViewStateStore<VS : ViewState, VE : ViewEffect, A : Action<VS, VE>> {
    val viewState: LiveData<VS>
    val viewEffect: LiveData<Event<VE>>
    fun sendAction(action: A)
    fun getCurrentState(): VS
}
