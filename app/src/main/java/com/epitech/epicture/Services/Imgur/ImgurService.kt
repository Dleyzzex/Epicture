package com.epitech.epicture.Services.Imgur

import com.epitech.epicture.Model.ImgurModels
import retrofit2.http.*

import retrofit2.Call
import retrofit2.http.GET


interface ImgurService
{
    /**
     * Get the user's image list
     */
    @GET("/3/account/me/images")
    fun getImages(@Header("Authorization") authHeader: String): Call<ImgurModels.Result>
}