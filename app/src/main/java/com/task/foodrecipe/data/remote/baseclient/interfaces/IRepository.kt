package com.task.foodrecipe.data.remote.baseclient.interfaces


import com.task.foodrecipe.data.remote.baseclient.ApiResponse
import com.task.foodrecipe.data.remote.baseclient.BaseApiResponse
import retrofit2.Response

internal interface IRepository {
    suspend fun <T : BaseApiResponse> executeSafely(call: suspend () -> Response<T>): ApiResponse<T>
}