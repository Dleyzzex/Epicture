package com.epitech.epicture.Activities

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.epitech.epicture.R
import kotlinx.android.synthetic.main.activity_upload.*
import androidx.loader.content.CursorLoader
import com.epitech.epicture.Model.ImgurModels
import com.epitech.epicture.Services.Retrofit.RetrofitService
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.MediaType;
import java.io.File

class UploadActivity : AppCompatActivity()
{
    var _accessToken: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)
        _accessToken = intent.getStringExtra("access_token")
        val button: Button = findViewById(R.id.btnUpload)
        val image = intent.getStringExtra("image")
        imageView.setImageURI(Uri.parse(image))
        button.setOnClickListener {
            uploadImage(Uri.parse(image))
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
    }

    private fun getRealPathFromURI(contentUri: Uri): String? {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val loader = CursorLoader(applicationContext, contentUri, proj, null, null, null)
        val cursor = loader.loadInBackground()
        val column_index = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor?.moveToFirst()
        val result = cursor?.getString(column_index!!)
        cursor?.close()
        return result
    }

    fun uploadImage(image: Uri)
    {
        val imgurApi = RetrofitService().createImgurService()
        val file = File(getRealPathFromURI(image!!))
        val requestFile = RequestBody.create(MediaType.parse(contentResolver.getType(image)), file)
        val imageBody = MultipartBody.Part.createFormData("image", file.name, requestFile)
        val call = imgurApi.uploadImage("Bearer " + _accessToken, imageBody)
        call.enqueue(object : Callback<ImgurModels.ResultImage> {
            override fun onFailure(call: Call<ImgurModels.ResultImage>, t: Throwable?) {}
            override fun onResponse(call: Call<ImgurModels.ResultImage>, response: Response<ImgurModels.ResultImage>) {
                if (response.isSuccessful) {
                    val res = response.body()

                } else {
                    println(response.errorBody())
                }
            }
        })
    }
}