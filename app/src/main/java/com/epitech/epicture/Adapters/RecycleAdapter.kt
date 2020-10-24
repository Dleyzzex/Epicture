package com.epitech.epicture.Adapters

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.epitech.epicture.Activities.HomeActivity
import com.epitech.epicture.ImageFragment
import com.epitech.epicture.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_recycle.view.*


class ImageGridKotlinAdapter(private val c: Context?, private val images: ArrayList<String>) :
    RecyclerView.Adapter<ImageGridKotlinAdapter.ColorViewHolder>() {

    var context: Context? = c

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        context = parent.context
        return ColorViewHolder(LayoutInflater.from(c).inflate(R.layout.item_picture, parent, false))
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val path = images[position]

        Picasso.get()
            .load(path)
            .resize(250, 250)
            .centerCrop()
            .into(holder.iv)

        holder.iv.setOnClickListener {
            //handle click event on image
            holder.iv.setColorFilter(Color.RED)
            val imagefrag = ImageFragment(holder.iv)
            val activity = context as HomeActivity
            activity.makeCurrentFragment(imagefrag)
        }
    }

    class ColorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iv = view.iv as ImageView
    }
}