package com.example.matrimonyinterview.view.homescreen.adapter

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter

object ImageBindingAdapter {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView: AppCompatImageView, url: String) {
        imageView.setImageResource(getDrawableIntByFileName(imageView.context,url))
            }

    private fun getDrawableIntByFileName(context: Context, fileName: String): Int {
        return context.resources.getIdentifier(fileName, "drawable", context.packageName)
    }
}