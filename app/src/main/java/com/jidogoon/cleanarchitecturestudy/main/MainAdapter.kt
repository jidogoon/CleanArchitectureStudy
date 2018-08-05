package com.jidogoon.cleanarchitecturestudy.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jidogoon.cleanarchitecturestudy.data.Photo
import com.jidogoon.cleanarchitecturestudy.image.IImageRequester

class MainAdapter(private val imageRequester: IImageRequester,
                  private val onPhotoClickListener: (Photo) -> Unit): RecyclerView.Adapter<MainItemViewHolder>() {
    private val items by lazy { mutableListOf<Photo>() }

    fun setItems(items: List<Photo>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainItemViewHolder {
        return MainItemViewHolder(parent, imageRequester, onPhotoClickListener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainItemViewHolder, position: Int) {
        holder.bind(items[position])
    }
}