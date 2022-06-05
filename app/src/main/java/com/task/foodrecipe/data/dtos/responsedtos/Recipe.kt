package com.task.foodrecipe.data.dtos.responsedtos


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    @SerializedName("approved_at")
    val approvedAt: Int?,
    @SerializedName("aspect_ratio")
    val aspectRatio: String?,
    @SerializedName("beauty_url")
    val beautyUrl: String?,
    @SerializedName("canonical_id")
    val canonicalId: String?,
    @SerializedName("compilations")
    val compilations: List<Compilation>?,
    @SerializedName("cook_time_minutes")
    val cookTimeMinutes: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("created_at")
    val createdAt: Int?,
    @SerializedName("credits")
    val credits: List<Credit>?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("draft_status")
    val draftStatus: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("inspired_by_url")
    val inspiredByUrl: String?,
    @SerializedName("instructions")
    val instructions: List<Instruction>?,
    @SerializedName("keywords")
    val keywords: String?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("num_servings")
    val numServings: Int?,
    @SerializedName("nutrition")
    val nutrition: Nutrition?,
    @SerializedName("nutrition_visibility")
    val nutritionVisibility: String?,
    @SerializedName("original_video_url")
    val originalVideoUrl: String?,
    @SerializedName("promotion")
    val promotion: String?,
    @SerializedName("renditions")
    val renditions: List<Rendition>?,
    @SerializedName("sections")
    val sections: List<Section>?,
    @SerializedName("servings_noun_plural")
    val servingsNounPlural: String?,
    @SerializedName("servings_noun_singular")
    val servingsNounSingular: String?,
    @SerializedName("show")
    val show: Show?,
    @SerializedName("show_id")
    val showId: Int?,
    @SerializedName("thumbnail_alt_text")
    val thumbnailAltText: String?,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String?,
    @SerializedName("tips_and_ratings_enabled")
    val tipsAndRatingsEnabled: Boolean?,
    @SerializedName("topics")
    val topics: List<Topic>?,
    @SerializedName("updated_at")
    val updatedAt: Int?,
    @SerializedName("user_ratings")
    val userRatings: UserRatings?,
    @SerializedName("video_ad_content")
    val videoAdContent: String?,
    @SerializedName("video_id")
    val videoId: Int?,
    @SerializedName("video_url")
    val videoUrl: String?
): Parcelable