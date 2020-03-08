package com.app.juawcevada.whatscookin.common.ui

import arrow.core.None
import arrow.core.Option

interface Action<VS : ViewState, VE : ViewEffect> {
    fun VS.reduce(): Option<VS> = None
    fun produceEffect(): Option<VE> = None
}