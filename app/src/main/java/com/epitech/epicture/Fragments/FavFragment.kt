package com.epitech.epicture

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
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
class FavFragment(accessToken: String, refreshToken: String, accountUsername: String) : Fragment() {

    var _favoriteList: MutableList<ImgurModels.DataImage>? = null
    val _accessToken = accessToken
    val _refreshToken = refreshToken
    val _accountUsername = accountUsername

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        updateFavoriteList()
        return inflater.inflate(R.layout.fragment_fav, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sglm = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rv.layoutManager = sglm
    }

    /**
     * Update the account fragment's imageList
     */
    fun updateFavoriteList()
    {
        val imgurApi = RetrofitService().createImgurService()
        val call = imgurApi.getFavorites("Bearer " + _accessToken, _accountUsername)
        call.enqueue(object: Callback<ImgurModels.ResultImage> {
            override fun onFailure(call: Call<ImgurModels.ResultImage>, t: Throwable?) {
                error("KO")
            }
            override fun onResponse(call: Call<ImgurModels.ResultImage>, response: Response<ImgurModels.ResultImage>) {
                if (response.isSuccessful) {
                    _favoriteList = ArrayList()
                    val picList = response.body()
                    var urlList: MutableList<ImgurModels.DataImage>? = ArrayList()
                    picList!!.data.forEach { pic ->
                        _favoriteList!!.add(pic)
                        urlList!!.add(pic)
                    }
                    //val sglm = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    //rv.layoutManager = sglm
                    val context: Context? = getContext()
                    val igka = ImageGridKotlinAdapter(context!!, urlList, _accessToken)
                    rv.adapter = igka
                }
                else {
                    println(response.errorBody())
                }
            }
        })
    }
}