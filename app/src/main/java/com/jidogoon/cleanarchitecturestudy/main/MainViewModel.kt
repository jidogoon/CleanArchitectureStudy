package com.jidogoon.cleanarchitecturestudy.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jidogoon.cleanarchitecturestudy.common.ViewStatus
import com.jidogoon.cleanarchitecturestudy.data.Photo
import com.jidogoon.cleanarchitecturestudy.repository.IRepository

class MainViewModel(private val repository: IRepository): ViewModel() {
    var viewStatus = MutableLiveData<ViewStatus>().apply {
        value = ViewStatus.NOT_SET
    }

    val photos = MutableLiveData<List<Photo>>()

    private val onPhotosReady = { photos: List<Photo> ->
        this.photos.postValue(photos)
        viewStatus.postValue(ViewStatus.DONE)
    }

    private val onRepositoryError = { error: Throwable ->
        viewStatus.postValue(ViewStatus.ERROR)
        error.printStackTrace()
    }

    fun requestPhotos() {
        viewStatus.postValue(ViewStatus.LOADING)
        repository.getPhotos(onPhotosReady, onRepositoryError)
    }
}