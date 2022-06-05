package com.task.foodrecipe.data.dtos.responsedtos


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Compilation(
    @SerializedName("approved_at")
    val approvedAt: Int?,
    @SerializedName("aspect_ratio")
    val aspectRatio: String?,
    @SerializedName("beauty_url")
    val beautyUrl: String?,
    @SerializedName("canonical_id")
    val canonicalId: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("created_at")
    val createdAt: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("draft_status")
    val draftStatus: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("is_shoppable")
    val isShoppable: Boolean?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("promotion")
    val promotion: String?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("thumbnail_alt_text")
    val thumbnailAltText: String?,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String?,
    @SerializedName("video_id")
    val videoId: Int?,
    @SerializedName("video_url")
    val videoUrl: String?
) : Parcelable