package com.task.foodrecipe.data.remote.microservices.foodrepos

import com.task.foodrecipe.data.dtos.responsedtos.GitUser
import com.task.foodrecipe.data.dtos.responsedtos.RecipeResponse
import com.task.foodrecipe.data.dtos.responsedtos.User
import com.task.foodrecipe.data.remote.baseclient.ApiResponse
import com.task.foodrecipe.data.remote.baseclient.BaseRepository
import javax.inject.Inject

class FoodRepositoryRemote @Inject constructor(
    private val service: FoodRetroService
) : BaseRepository(), FoodRepoApi {

    companion object {
        const val URL_GET_RECIPE_LIST = "/recipes/list"
        const val URL_GET_RECIPE_SIMILAR_LIST = "/recipes/list-similarities"
    }

    override suspend fun getRecipeList(
        page: Int,
        size: Int,
        query: String?
    ): ApiResponse<RecipeResponse> {
        return executeSafely(call = {
            service.getRecipeList(
                page = page,
                size = size,
                query = query
            )
        })
    }

    override suspend fun getRecipeSimilarList(recipeId: Int): ApiResponse<RecipeResponse> {
        return executeSafely(call = {
            service.getRecipeSimilarList(
                recipeId = recipeId
            )
        })
    }
}