package com.app.productapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapters {

    @BindingAdapter("bind:image")
    @JvmStatic
    fun setImage(imageView: ImageView, url: String?) {
        if (!url.isNullOrEmpty()) {
            Glide.with(imageView.context)
                .load(url)
                .into(imageView)
        }
    }
}