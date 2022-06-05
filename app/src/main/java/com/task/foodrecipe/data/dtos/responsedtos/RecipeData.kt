package com.task.foodrecipe.data.dtos.responsedtos


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeData(
    @SerializedName("approved_at")
    val approvedAt: Int?,
    @SerializedName("aspect_ratio")
    val aspectRatio: String?,
    @SerializedName("beauty_url")
    val beautyUrl: String?,
    @SerializedName("buzz_id")
    val buzzId: String?,
    @SerializedName("canonical_id")
    val canonicalId: String?,
    @SerializedName("compilations")
    val compilations: List<Compilation>?,
    @SerializedName("cook_time_minutes")
    val cookTimeMinutes: Int?,
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
    @SerializedName("is_shoppable")
    val isShoppable: Boolean?,
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
    @SerializedName("original_video_url")
    val originalVideoUrl: String?,
    @SerializedName("recipes")
    val recipes: List<Recipe>?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("thumbnail_alt_text")
    val thumbnailAltText: String?,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String?,
    @SerializedName("topics")
    val topics: List<Topic>?,
    @SerializedName("total_time_minutes")
    val totalTimeMinutes: Int?,
    @SerializedName("updated_at")
    val updatedAt: Int?,
    @SerializedName("user_ratings")
    val userRatings: UserRatings?,
    @SerializedName("video_id")
    val videoId: String?,
    @SerializedName("video_url")
    val videoUrl: String?,
) : Parcelable