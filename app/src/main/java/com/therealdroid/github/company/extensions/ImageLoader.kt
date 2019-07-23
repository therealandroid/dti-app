package com.therealdroid.github.company.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(drawable: Drawable?) {
    Glide.with(context).load(drawable).into(this)
}