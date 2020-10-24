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

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SearchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sglm = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rv.layoutManager = sglm

        val context: Context? = getContext()

        val imageList = ArrayList<String>()
        imageList.add("https://i.imgur.com/v8hn4T0.jpg")
        imageList.add("https://i.imgur.com/v8hn4T0.jpg")
        imageList.add("https://i.imgur.com/v8hn4T0.jpg")
        imageList.add("https://i.imgur.com/v8hn4T0.jpg")
        imageList.add("https://i.imgur.com/v8hn4T0.jpg")
        imageList.add("https://i.imgur.com/v8hn4T0.jpg")
        imageList.add("https://i.imgur.com/v8hn4T0.jpg")

        val igka = ImageGridKotlinAdapter(context, imageList)
        rv.adapter = igka

        val SearchButton: SearchView = view.findViewById(R.id.searchView)
        var searchString: String? = null

        SearchButton.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                searchString = p0
                println(searchString)
                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
    }
}