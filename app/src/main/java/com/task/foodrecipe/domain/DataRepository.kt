package com.task.foodrecipe.domain

import com.task.foodrecipe.data.dtos.responsedtos.RecipeResponse
import com.task.foodrecipe.data.dtos.responsedtos.User
import com.task.foodrecipe.data.remote.baseclient.ApiResponse
import com.task.foodrecipe.data.remote.microservices.foodrepos.FoodRepoApi
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val remoteRepository: FoodRepoApi,
) : IDataRepository {

    override suspend fun getRecipes(
        page: Int,
        size: Int,
        query: String?
    ): ApiResponse<RecipeResponse> {
        return remoteRepository.getRecipeList(
            page = page,
            size = size,
            query = query
        )
    }

    override suspend fun getSimilarRecipes(recipeId: Int): ApiResponse<RecipeResponse> {
        return remoteRepository.getRecipeSimilarList(
            recipeId = recipeId
        )
    }
}