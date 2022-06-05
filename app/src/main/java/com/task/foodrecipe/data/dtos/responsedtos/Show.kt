package com.task.foodrecipe.data.dtos.responsedtos


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Show(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
) : Parcelable