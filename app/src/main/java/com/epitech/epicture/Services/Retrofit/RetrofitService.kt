package com.epitech.epicture.Services.Retrofit

import com.epitech.epicture.Services.Imgur.ImgurService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService
{
    fun createImgurService() : ImgurService
    {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.imgur.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ImgurService::class.java)
    }
}