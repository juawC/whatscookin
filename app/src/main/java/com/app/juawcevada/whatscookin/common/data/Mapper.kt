package com.app.juawcevada.whatscookin.common.data

interface Mapper<I, O> {
    fun map(input: I): O
}