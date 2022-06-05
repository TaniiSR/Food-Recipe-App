package com.task.foodrecipe.domain

import com.task.foodrecipe.data.dtos.responsedtos.RecipeResponse
import com.task.foodrecipe.data.remote.baseclient.ApiResponse

interface IDataRepository {

    suspend fun getRecipes(
        page: Int,
        size: Int,
        query: String?
    ): ApiResponse<RecipeResponse>

    suspend fun getSimilarRecipes(
        recipeId: Int
    ): ApiResponse<RecipeResponse>
}