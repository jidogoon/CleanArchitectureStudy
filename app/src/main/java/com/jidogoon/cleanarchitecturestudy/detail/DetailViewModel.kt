package com.jidogoon.cleanarchitecturestudy.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jidogoon.cleanarchitecturestudy.common.ViewStatus
import com.jidogoon.cleanarchitecturestudy.data.Photo
import com.jidogoon.cleanarchitecturestudy.repository.IRepository

class DetailViewModel(private val repository: IRepository): ViewModel() {
    var viewStatus = MutableLiveData<ViewStatus>().apply {
        value = ViewStatus.NOT_SET
    }

    val photoData = MutableLiveData<Photo>()

    private val onPhotoDataReady = { photoData: Photo ->
        this.photoData.postValue(photoData)
        viewStatus.postValue(ViewStatus.DONE)
    }

    private val onRepositoryError = { error: Throwable ->
        viewStatus.postValue(ViewStatus.ERROR)
        error.printStackTrace()
    }

    fun requestPhoto(photoId: Int) {
        viewStatus.postValue(ViewStatus.LOADING)
        repository.getPhoto(photoId, onPhotoDataReady, onRepositoryError)
    }
}