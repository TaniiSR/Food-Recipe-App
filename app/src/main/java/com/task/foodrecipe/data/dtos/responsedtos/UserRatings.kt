package com.task.foodrecipe.data.dtos.responsedtos


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRatings(
    @SerializedName("count_negative")
    val countNegative: Int?,
    @SerializedName("count_positive")
    val countPositive: Int?,
    @SerializedName("score")
    val score: Double?
) : Parcelable