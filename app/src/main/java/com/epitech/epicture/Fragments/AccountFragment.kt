package com.epitech.epicture

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.epitech.epicture.Adapters.ImageGridKotlinAdapter
import com.epitech.epicture.Model.ImgurModels
import com.epitech.epicture.Services.Retrofit.RetrofitService
import kotlinx.android.synthetic.main.fragment_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AccountFragment(accessToken: String, refreshToken: String, accountUsername: String) : Fragment() {

    var _imageList: MutableList<ImgurModels.DataImage>? = null
    val _accessToken = accessToken
    val _refreshToken = refreshToken
    val _accountUsername = accountUsername

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        updateImageList()
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.username).setText("Hello " + _accountUsername + " !")
    }

    /**
     * Update the account fragment's imageList
     */
    fun updateImageList()
    {
        val imgurApi = RetrofitService().createImgurService()
        val call = imgurApi.getImages("Bearer " + _accessToken)
        call.enqueue(object: Callback<ImgurModels.ResultImage> {
            override fun onFailure(call: Call<ImgurModels.ResultImage>, t: Throwable?) {
                error("KO")
            }
            override fun onResponse(call: Call<ImgurModels.ResultImage>, response: Response<ImgurModels.ResultImage>) {
                    if (response.isSuccessful) {
                        _imageList = ArrayList()
                        val picList = response.body()
                        val urlList = ArrayList<String>()
                        picList!!.data.forEach { pic ->
                            _imageList!!.add(pic)
                            urlList.add(pic.link)
                        }
                        val sglm = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                        rv.layoutManager = sglm
                        val context: Context? = getContext()
                        val igka = ImageGridKotlinAdapter(context, urlList)
                        rv.adapter = igka
                    }
                    else {
                        println(response.errorBody())
                    }
            }
        })
    }
}