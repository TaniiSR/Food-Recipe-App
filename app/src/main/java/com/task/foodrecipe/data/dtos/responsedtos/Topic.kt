package com.task.foodrecipe.data.dtos.responsedtos


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Topic(
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?
) : Parcelable