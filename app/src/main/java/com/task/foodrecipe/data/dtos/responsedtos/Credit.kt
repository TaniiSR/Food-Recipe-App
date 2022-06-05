package com.task.foodrecipe.data.dtos.responsedtos


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Credit(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("type")
    val type: String?
) : Parcelable