package com.epitech.epicture.Model

class ImgurModels
{
    data class DataImage(
        val id: String,
        val title: String,
        val description: String,
        val link: String
    )

    data class ResultImage(val data: List<DataImage>, val success: Boolean, val status: String)
    data class ResultString(val data: String, val success: Boolean, val status: String)
}