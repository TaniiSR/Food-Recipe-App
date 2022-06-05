package com.task.foodrecipe.data.remote.microservices.foodrepos

import com.task.foodrecipe.data.dtos.responsedtos.GitUser
import com.task.foodrecipe.data.dtos.responsedtos.RecipeResponse
import com.task.foodrecipe.data.dtos.responsedtos.User
import com.task.foodrecipe.data.remote.baseclient.ApiResponse


interface FoodRepoApi {
    suspend fun getRecipeList(page: Int, size: Int, query: String?): ApiResponse<RecipeResponse>
    suspend fun getRecipeSimilarList(
        recipeId: Int
    ): ApiResponse<RecipeResponse>

}