package com.task.foodrecipe.data.dtos.responsedtos


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ingredient(
    @SerializedName("created_at")
    val createdAt: Int?,
    @SerializedName("display_plural")
    val displayPlural: String?,
    @SerializedName("display_singular")
    val displaySingular: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("updated_at")
    val updatedAt: Int?
) : Parcelable