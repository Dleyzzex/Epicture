package com.epitech.epicture

import android.os.Bundle
import android.os.Handler
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.coroutines.withTimeout
import android.content.Intent
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.net.Uri
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.epitech.epicture.Model.ImgurModels
import com.epitech.epicture.Services.Retrofit.RetrofitService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_upload.*
import kotlinx.android.synthetic.main.adapter_recycle.view.*
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageActivity : AppCompatActivity() {

    private var timeout = 1500
    var _accessToken: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        var image_url = intent.getStringExtra("image")
        var image_des= intent.getStringExtra("image_des")
        var image_title = intent.getStringExtra("image_title")
        var image_id: String = intent.getStringExtra("image_id").toString()
        var delete_hash: String = intent.getStringExtra("delete_hash").toString()
        var delete_status: Boolean? = intent.getBooleanExtra("delete_status", false)
        _accessToken = intent.getStringExtra("accessToken")

        val imageView: ImageView = findViewById(R.id.image_mod)
        Picasso.get().load(image_url).into(imageView)
        findViewById<Button>(R.id.like).setOnClickListener {
            faveImage(image_id)
            finish()
        }
        findViewById<Button>(R.id.delete).setOnClickListener {
            deleteImage(delete_hash)
            finish()
        }
        if (delete_status == false)
                findViewById<Button>(R.id.delete).setVisibility(View.GONE);

    }

    /**
     * favorite and unfavorite an image
     */
    fun faveImage(idImage : String)
    {
        val imgurApi = RetrofitService().createImgurService()
        val call = imgurApi.favoriteImage("Bearer " + _accessToken, idImage)
        call.enqueue(object: Callback<ImgurModels.ResultString> {
            override fun onFailure(call: Call<ImgurModels.ResultString>, t: Throwable?) {
                error("KO")
            }
            override fun onResponse(call: Call<ImgurModels.ResultString>, response: Response<ImgurModels.ResultString>) {
                if (response.isSuccessful) {
                    val res = response.body()
                    if (res != null) {
                        if (res.data == "favorited")
                            println("image $idImage favorited")
                        else
                            println("image $idImage defavorited")
                    }
                }
                else {
                    println(response.errorBody())
                }
            }
        })
    }


    /**
     * Delete an image
     */
    fun deleteImage(deletehash : String)
    {
        val imgurApi = RetrofitService().createImgurService()
        val call = imgurApi.deleteImage("Bearer " + _accessToken, deletehash)
        call.enqueue(object: Callback<ImgurModels.ResultBool> {
            override fun onFailure(call: Call<ImgurModels.ResultBool>, t: Throwable?) {
                error("KO")
            }
            override fun onResponse(call: Call<ImgurModels.ResultBool>, response: Response<ImgurModels.ResultBool>) {
                if (response.isSuccessful) {
                    println("image delete")
                }
                else {
                    println(response.errorBody())
                }
            }
        })
    }
}