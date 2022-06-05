package com.task.foodrecipe.data.dtos.responsedtos


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Unit(
    @SerializedName("abbreviation")
    val abbreviation: String?,
    @SerializedName("display_plural")
    val displayPlural: String?,
    @SerializedName("display_singular")
    val displaySingular: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("system")
    val system: String?
) : Parcelable