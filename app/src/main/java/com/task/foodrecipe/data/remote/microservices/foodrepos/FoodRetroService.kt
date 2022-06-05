package com.task.foodrecipe.data.remote.microservices.foodrepos

import com.task.foodrecipe.data.dtos.responsedtos.GitUser
import com.task.foodrecipe.data.dtos.responsedtos.RecipeResponse
import com.task.foodrecipe.data.dtos.responsedtos.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FoodRetroService {

    @GET(FoodRepositoryRemote.URL_GET_RECIPE_LIST)
    suspend fun getRecipeList(
        @Query("from") page: Int,
        @Query("size") size: Int,
        @Query("q") query: String?
    ): Response<RecipeResponse>

    @GET(FoodRepositoryRemote.URL_GET_RECIPE_SIMILAR_LIST)
    suspend fun getRecipeSimilarList(
        @Query("recipe_id") recipeId: Int,
    ): Response<RecipeResponse>

}