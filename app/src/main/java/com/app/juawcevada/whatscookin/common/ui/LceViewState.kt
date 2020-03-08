package com.app.juawcevada.whatscookin.common.ui

interface LceViewState : ViewState {
    val isLoading: Boolean
    val errorMessage: Int?
}