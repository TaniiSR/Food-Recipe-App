package com.task.foodrecipe.data.dtos.responsedtos


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rendition(
    @SerializedName("aspect")
    val aspect: String?,
    @SerializedName("bit_rate")
    val bitRate: Int?,
    @SerializedName("container")
    val container: String?,
    @SerializedName("content_type")
    val contentType: String?,
    @SerializedName("duration")
    val duration: Int?,
    @SerializedName("file_size")
    val fileSize: Int?,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("poster_url")
    val posterUrl: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("width")
    val width: Int?
) : Parcelable