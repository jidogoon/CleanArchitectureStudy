package com.jidogoon.cleanarchitecturestudy

import com.jidogoon.cleanarchitecturestudy.data.Photo
import com.jidogoon.cleanarchitecturestudy.repository.IRepository

class MockRepository: IRepository {
    override fun getPhotos(onReady: (List<Photo>) -> Unit, onError: (Throwable) -> Unit) {
        try {
            onReady(TestDataReader().photosData)
        } catch (e: Exception) {
            onError(e)
        }
    }

    override fun getPhoto(photoId: Int, onReady: (Photo) -> Unit, onError: (Throwable) -> Unit) {
        try {
            onReady(TestDataReader().photoData)
        } catch (e: Exception) {
            onError(e)
        }
    }
}