package com.task.foodrecipe.data.dtos.responsedtos


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Instruction(
    @SerializedName("appliance")
    val appliance: String?,
    @SerializedName("display_text")
    val displayText: String?,
    @SerializedName("end_time")
    val endTime: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("position")
    val position: Int?,
    @SerializedName("start_time")
    val startTime: Int?,
    @SerializedName("temperature")
    val temperature: String?
) : Parcelable