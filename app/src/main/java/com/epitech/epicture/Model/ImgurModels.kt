package com.epitech.epicture.Model

class ImgurModels
{
    data class DataImage(
        val id: String,
        var title: String,
        val description: String,
        val link: String,
        val deletehash: String
    )

    data class ResultImage(val data: List<DataImage>, val success: Boolean, val status: String)
    data class ResultString(val data: String, val success: Boolean, val status: String)
    data class ResultBool(val data: Boolean, val success: Boolean, val status: String)

    data class DataSearch (
            val id: String,
            val title: String,
            val description: String,
            val link: String,
            val is_album: Boolean,
            val images: List<DataImage>
    )

    data class ResultSearch(val data: List<DataSearch>, val success: Boolean, val status: String)
}