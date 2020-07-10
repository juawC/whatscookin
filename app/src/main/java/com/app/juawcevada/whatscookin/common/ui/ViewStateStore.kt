package com.app.juawcevada.whatscookin.common.ui

import androidx.lifecycle.LiveData
import com.app.juawcevada.whatscookin.common.util.Event

interface ViewStateStore<VS : ViewState, VE : ViewEffect> {
    val viewState: LiveData<VS>
    val viewEffect: LiveData<Event<VE>>
}
