package com.gtdan.marvelucm.adapter

import android.content.ClipData
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gtdan.marvelucm.ApiStatus
import com.gtdan.marvelucm.R
import com.gtdan.marvelucm.network.model.ItemGalleryModel
import com.gtdan.marvelucm.network.model.ItemModel
import com.gtdan.marvelucm.network.model.ItemUpcomingModel


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<ItemModel>?) {
    val adapter = recyclerView.adapter as ItemsListAdapter
    adapter.submitList(data)
}

@BindingAdapter("listDataUpcoming")
fun bindRecyclerViewUpcoming(recyclerView: RecyclerView, data: List<ItemUpcomingModel>?) {
    val adapter = recyclerView.adapter as ItemUpcomingAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatus")
fun bindApiStatus(statusImageView: ImageView, status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }

        ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUrl = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUrl) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.logo)
        }
    }
}