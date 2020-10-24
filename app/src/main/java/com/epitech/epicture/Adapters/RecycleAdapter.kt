package com.epitech.epicture.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import com.epitech.epicture.ImageActivity
import com.epitech.epicture.Model.ImgurModels
import com.epitech.epicture.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_recycle.view.*


class ImageGridKotlinAdapter(private val c: Context, private val images:  MutableList<ImgurModels.DataImage>?, private val token: String) :
    RecyclerView.Adapter<ImageGridKotlinAdapter.ColorViewHolder>() {

    override fun getItemCount(): Int {
        return images!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        return ColorViewHolder(LayoutInflater.from(c).inflate(R.layout.item_picture, parent, false))
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val path = images?.get(position)?.link

        Picasso.get()
            .load(path)
            .resize(250, 250)
            .centerCrop()
            .into(holder.iv)

        holder.iv.setOnClickListener {
            val intent = Intent(c, ImageActivity::class.java).apply {
                putExtra("image", images?.get(position)?.link)
                putExtra("image_des", images?.get(position)?.description)
                putExtra("image_title", images?.get(position)?.title)
                putExtra("image_id", images?.get(position)?.id)
                putExtra("accessToken", token)

            }
            startActivity(c, intent, null)
        }
    }

    class ColorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iv = view.iv as ImageView
    }
}