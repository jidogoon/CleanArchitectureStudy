package com.jidogoon.cleanarchitecturestudy.repository

import android.content.Context
import com.google.gson.Gson
import com.jidogoon.cleanarchitecturestudy.data.Photo
import kotlinx.coroutines.experimental.launch
import com.google.gson.reflect.TypeToken

class LocalRepository(private val context: Context): IRepository {
    override fun getPhotos(onReady: (List<Photo>) -> Unit, onError: (Throwable) -> Unit) {
        launch {
            try {
                getPhotos()?.let(onReady)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    override fun getPhoto(photoId: Int, onReady: (Photo) -> Unit, onError: (Throwable) -> Unit) {
        launch {
            try {
                getPhoto()?.let(onReady)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    private fun getPhotos(): List<Photo>? {
        val jsonData = context.assets.open("photos.json").bufferedReader().use {
            it.readText()
        }

        val listType = object : TypeToken<List<Photo>>() {}.type
        return Gson().fromJson<List<Photo>>(jsonData, listType)
    }

    private fun getPhoto(): Photo? {
        val jsonData = context.assets.open("photo_01.json").bufferedReader().use {
            it.readText()
        }

        return Gson().fromJson(jsonData, Photo::class.java)
    }
}