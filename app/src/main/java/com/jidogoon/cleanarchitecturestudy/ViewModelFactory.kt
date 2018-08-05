package com.jidogoon.cleanarchitecturestudy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jidogoon.cleanarchitecturestudy.detail.DetailViewModel
import com.jidogoon.cleanarchitecturestudy.main.MainViewModel
import com.jidogoon.cleanarchitecturestudy.repository.IRepository

class ViewModelFactory(): ViewModelProvider.Factory {
    private lateinit var repository: IRepository
    constructor(repository: IRepository): this() {
        this.repository = repository
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(repository) as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}