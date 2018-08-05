package com.jidogoon.cleanarchitecturestudy.repository

import com.jidogoon.cleanarchitecturestudy.data.Photo

interface IRepository {
    fun getPhotos(onReady: (List<Photo>) -> Unit, onError: (Throwable) -> Unit)
    fun getPhoto(photoId: Int, onReady: (Photo) -> Unit, onError: (Throwable) -> Unit)
}