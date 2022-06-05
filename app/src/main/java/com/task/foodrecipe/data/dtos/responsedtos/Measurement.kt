package com.task.foodrecipe.data.dtos.responsedtos


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Measurement(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("quantity")
    val quantity: String?,
    @SerializedName("unit")
    val unit: Unit?
) : Parcelable