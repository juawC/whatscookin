package com.app.juawcevada.whatscookin.common.data

class ListMapper<I, O>(
    private val mapper: Mapper<I, O>
) : Mapper<List<I>, List<O>> {
    override fun map(input: List<I>): List<O> = input.map(mapper::map)
}
