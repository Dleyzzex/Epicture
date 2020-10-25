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
    fun getImages(
        @Header("Authorization") authHeader: String
    ): Call<ImgurModels.ResultImage>

    /**
     * Get the user's favorite list
     */
    @GET("/3/account/{username}/favorites")
    fun getFavorites(
        @Header("Authorization") authHeader: String,
        @Path("username") imageId: String
    ): Call<ImgurModels.ResultImage>

    /**
     * Get a random image list
     */
    @GET("/3/gallery/random/random/0")
    fun getSearchRandom(
        @Header("Authorization") authHeader: String
    ): Call<ImgurModels.ResultSearch>

    /**
     * Get a image list with the query parameter
     */
    @GET("/3/gallery/search/{sort}/{window}/{page}")
    fun getSearch(
        @Header("Authorization") authHeader: String,
        @Path("page") page: String,
        @Query("q") query: String
    ): Call<ImgurModels.ResultSearch>

    /**
     * Favorite and unfavorite an image
     */
    @POST("/3/image/{imageHash}/favorite")
    fun favoriteImage(
        @Header("Authorization") authHeader: String,
        @Path("imageHash") imageId: String
    ): Call<ImgurModels.ResultString>

    /**
     * Upload the image
     */
    @Multipart
    @POST("/3/image")
    fun uploadImage(
        @Header("Authorization") authHeader: String,
        @Part file: MultipartBody.Part
    ): Call<ImgurModels.ResultImage>

    /**
     * Delete an image
     */
    @DELETE("/3/image/{deletehash}")
    fun deleteImage(
            @Header("Authorization") authHeader: String,
            @Path("deletehash") deletehash: String
    ): Call<ImgurModels.ResultBool>
}