package com.app.juawcevada.whatscookin.data.recipe.api

import com.app.juawcevada.whatscookin.data.recipe.model.RecipeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeService {
    @GET("recipes/{id}/information")
    suspend fun getRecipe(
        @Path("id") id: String
    ): Response<RecipeResponse>

}
