package com.app.juawcevada.whatscookin.data.search.api

import com.app.juawcevada.whatscookin.data.search.model.IngredientSearchItemResponse
import com.app.juawcevada.whatscookin.data.search.model.RecipeSearchItemResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("recipes/findByIngredients")
    suspend fun searchByIngredients(
        @Query("ingredients") ingredients: String,
        @Query("number") numberOfResults: Int,
        @Query("ranking") ranking: Int = 2
    ): Response<List<RecipeSearchItemResponse>>

    @GET("food/ingredients/autocomplete")
    suspend fun searchForIngredients(
        @Query("query") partialName: String,
        @Query("number") numberOfResults: Int
    ): Response<List<IngredientSearchItemResponse>>
}