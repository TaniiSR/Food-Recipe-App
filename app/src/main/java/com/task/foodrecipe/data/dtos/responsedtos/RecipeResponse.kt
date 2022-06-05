package com.task.foodrecipe.data.dtos.responsedtos


import com.google.gson.annotations.SerializedName
import com.task.foodrecipe.data.remote.baseclient.BaseApiResponse

data class RecipeResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("results")
    val results: List<RecipeData>?
) : BaseApiResponse()