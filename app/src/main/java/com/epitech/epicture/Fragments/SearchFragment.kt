package com.epitech.epicture

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.epitech.epicture.Adapters.ImageGridKotlinAdapter
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SearchFragment : Fragment() {

    private val mListener: OnFragmentInteractionListener? = null

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
    }

    public interface OnFragmentInteractionListener {
        fun messageFromParentFragment(uri: Uri?)
    }
}