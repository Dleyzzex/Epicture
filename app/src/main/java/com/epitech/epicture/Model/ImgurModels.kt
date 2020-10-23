package com.epitech.epicture.Model

class ImgurModels
{
    data class DataImage(
        val id: String,
        val title: String,
        val description: String,
        val datetime: Long,
        val type: String,
        val animated: Boolean,
        val width: Int,
        val height: Int,
        val size: Long,
        val views: Int,
        val bandwidth: Long,
        val vote: String,
        val favorite: Boolean,
        val nsfw: String,
        val section: String,
        val account_url: String,
        val account_id: Long,
        val is_ad: Boolean,
        val has_sound: Boolean,
        val is_most_viral: Boolean,
        val tags: List<String>,
        val in_gallery: Boolean,
        val deletehash: String,
        val name: String,
        val link: String
    )

    data class ResultImage(val data: List<DataImage>, val success: Boolean, val status: String)
    data class ResultString(val data: String, val success: Boolean, val status: String)
}