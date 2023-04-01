package com.keysoc.codingtest.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.keysoc.codingtest.R

@BindingAdapter("glideSrc")
fun ImageView.bindGlideSrc(imageUrl: String?) {
    if (imageUrl.isNullOrEmpty()) {
        setImageResource(R.drawable.item_no_photo_preview)
        scaleType = ImageView.ScaleType.CENTER_CROP
    } else {
        Glide.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.item_no_photo_preview)
            .centerCrop()
            .into(this)
    }
}