package com.task.foodrecipe.data.dtos.responsedtos


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Section(
    @SerializedName("components")
    val components: List<Component>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("position")
    val position: Int?
) : Parcelable