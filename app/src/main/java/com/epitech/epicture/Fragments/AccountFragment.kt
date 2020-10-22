package com.epitech.epicture

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.epitech.epicture.Model.ImgurModels
import com.epitech.epicture.Services.Retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AccountFragment : Fragment() {

    var _imageList: MutableList<ImgurModels.DataImage>? = null

    companion object {
        fun newInstance(_accessToken: String, _refreshToken: String, _accountUsername: String): AccountFragment {
            val args = Bundle()
            args.putString("_accessToken", _accessToken)
            args.putString("_refreshToken", _refreshToken)
            args.putString("_accountUsername", _accountUsername)
            val fragment = AccountFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        updateImageList()
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * Update the account fragment's imageList
     */
    fun updateImageList()
    {
        val imgurApi = RetrofitService().createImgurService()
        val _accessToken = arguments?.getString("_accessToken")
        val call = imgurApi.getImages("Bearer " + _accessToken)
        call.enqueue(object: Callback<ImgurModels.Result> {
            override fun onFailure(call: Call<ImgurModels.Result>, t: Throwable?) {
                error("KO")
            }
            override fun onResponse(call: Call<ImgurModels.Result>, response: Response<ImgurModels.Result>) {
                    if (response.isSuccessful) {
                        _imageList = ArrayList()
                        val picList = response.body()
                        picList!!.data.forEach { pic ->
                            _imageList!!.add(pic)
                        }
                        //println(_pictureList)
                    }
                    else {
                        println(response.errorBody())
                    }
            }
        })
    }
}