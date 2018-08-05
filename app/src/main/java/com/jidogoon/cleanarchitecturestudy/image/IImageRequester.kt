package com.jidogoon.cleanarchitecturestudy.image

import android.widget.ImageView

interface IImageRequester {
    fun setUrl(imageView: ImageView, url: String?)
}