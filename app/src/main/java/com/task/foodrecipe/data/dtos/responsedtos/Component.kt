package com.task.foodrecipe.data.dtos.responsedtos


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Component(
    @SerializedName("extra_comment")
    val extraComment: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("ingredient")
    val ingredient: Ingredient?,
    @SerializedName("measurements")
    val measurements: List<Measurement>?,
    @SerializedName("position")
    val position: Int?,
    @SerializedName("raw_text")
    val rawText: String?
) :Parcelable