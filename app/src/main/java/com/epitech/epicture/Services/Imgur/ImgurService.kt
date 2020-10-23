package com.epitech.epicture.Services.Imgur

import com.epitech.epicture.Model.ImgurModels
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.POST
import retrofit2.http.Multipart


interface ImgurService
{
    /**
     * Get the user's image list
     */
    @GET("/3/account/me/images")
    fun getImages(@Header("Authorization") authHeader: String): Call<ImgurModels.ResultImage>

    @POST("/3/image/{imageHash}/favorite")
    fun favoriteImage(
        @Header("Authorization") authHeader: String,
        @Path("imageHash") imageId: String
    ): Call<ImgurModels.ResultString>

    @Multipart
    @POST("/3/image")
    fun uploadImage(
        @Header("Authorization") authHeader: String,
        @Part file: MultipartBody.Part
    ): Call<ImgurModels.ResultImage>
}