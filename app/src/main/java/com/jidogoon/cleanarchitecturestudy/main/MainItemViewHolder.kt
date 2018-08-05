package com.jidogoon.cleanarchitecturestudy.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jidogoon.cleanarchitecturestudy.R
import com.jidogoon.cleanarchitecturestudy.data.Photo
import com.jidogoon.cleanarchitecturestudy.extension.setUrl
import com.jidogoon.cleanarchitecturestudy.image.IImageRequester
import kotlinx.android.synthetic.main.list_item_photo.view.*

class MainItemViewHolder(parent: ViewGroup,
                         private val imageRequester: IImageRequester,
                         private val onPhotoClickListener: (Photo) -> Unit): RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_photo, parent, false)) {

    fun bind(photo: Photo) {
        with(itemView) {
            title.text = photo.title
            photoThumb.setUrl(imageRequester, photo.thumbnailUrl)
            setOnClickListener { onPhotoClickListener(photo) }
        }
    }
}