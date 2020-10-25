package com.epitech.epicture

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.epitech.epicture.Adapters.ImageGridKotlinAdapter
import com.epitech.epicture.Model.ImgurModels
import com.epitech.epicture.Services.Retrofit.RetrofitService
import kotlinx.android.synthetic.main.fragment_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SearchFragment(accessToken: String) : Fragment() {

    val _accessToken = accessToken
    var _imageList: MutableList<ImgurModels.DataImage>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        updateSearchListRandom()
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val SearchButton: SearchView = view.findViewById(R.id.searchView)
        var searchString: String? = null

        SearchButton.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                searchString = p0
                searchString?.let { updateSearchList(it) }

                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
    }

    override fun onResume() {
        super.onResume()
        updateSearchListRandom()
    }

    /**
     * Update the search fragment's imageList with random method
     */
    fun updateSearchListRandom()
    {
        val imgurApi = RetrofitService().createImgurService()
        val call = imgurApi.getSearchRandom("Bearer " + _accessToken)
        call.enqueue(object: Callback<ImgurModels.ResultSearch> {
            override fun onFailure(call: Call<ImgurModels.ResultSearch>, t: Throwable?) {
                error("KO")
            }
            override fun onResponse(call: Call<ImgurModels.ResultSearch>, response: Response<ImgurModels.ResultSearch>) {
                if (response.isSuccessful) {
                    _imageList = java.util.ArrayList()
                    val picList = response.body()?.data
                    var urlList: MutableList<ImgurModels.DataImage>? = ArrayList()
                    picList!!.forEach { pic ->
                        if (pic.is_album) {
                            if (pic.images[0].link.endsWith(".jpg") || pic.images[0].link.endsWith(".png")) {
                                urlList!!.add(pic.images[0])
                                _imageList!!.add(pic.images[0])
                            }
                        }
                    }
                    val sglm = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    rv.layoutManager = sglm
                    val context: Context? = getContext()
                    val igka = ImageGridKotlinAdapter(context!!, urlList!!, _accessToken, false)
                    rv.adapter = igka
                }
                else {
                    println(response.errorBody())
                }
            }
        })
    }

    /**
     * Update the search fragment's imageList with the string query string
     */
    fun updateSearchList(query : String)
    {
        val imgurApi = RetrofitService().createImgurService()
        val call = imgurApi.getSearch("Bearer " + _accessToken, "0", query)
        call.enqueue(object: Callback<ImgurModels.ResultSearch> {
            override fun onFailure(call: Call<ImgurModels.ResultSearch>, t: Throwable?) {
                error("KO")
            }
            override fun onResponse(call: Call<ImgurModels.ResultSearch>, response: Response<ImgurModels.ResultSearch>) {
                if (response.isSuccessful) {
                    _imageList = java.util.ArrayList()
                    val picList = response.body()?.data
                    var urlList: MutableList<ImgurModels.DataImage>? = ArrayList()
                    picList!!.forEach { pic ->
                        if (pic.is_album) {
                            if (pic.images[0].link.endsWith(".jpg") || pic.images[0].link.endsWith(".png")) {
                                urlList!!.add(pic.images[0])
                                _imageList!!.add(pic.images[0])
                            }
                        }
                    }
                    val sglm = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    rv.layoutManager = sglm
                    val context: Context? = getContext()
                    val igka = ImageGridKotlinAdapter(context!!, urlList!!, _accessToken, false)
                    rv.adapter = igka
                }
                else {
                    println(response.errorBody())
                }
            }
        })
    }
}