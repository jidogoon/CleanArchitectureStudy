package com.jidogoon.cleanarchitecturestudy.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.jidogoon.cleanarchitecturestudy.R
import com.jidogoon.cleanarchitecturestudy.ViewModelFactory
import com.jidogoon.cleanarchitecturestudy.common.ViewStatus
import com.jidogoon.cleanarchitecturestudy.data.Photo
import com.jidogoon.cleanarchitecturestudy.extension.hide
import com.jidogoon.cleanarchitecturestudy.extension.setUrl
import com.jidogoon.cleanarchitecturestudy.extension.show
import com.jidogoon.cleanarchitecturestudy.image.IImageRequester
import com.jidogoon.cleanarchitecturestudy.image.ImageRequester
import com.jidogoon.cleanarchitecturestudy.repository.RemoteRepository
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity: AppCompatActivity() {
    companion object {
        private const val PHOTO_ID = "PHOTO_ID"
        fun startActivity(activity: Activity, photoId: Int) {
            val intent = Intent(activity, DetailActivity::class.java).apply {
                putExtra(PHOTO_ID, photoId)
            }
            activity.startActivity(intent)
        }
    }

    private lateinit var viewModel: DetailViewModel
    private lateinit var imageRequester: IImageRequester

    private val statusObserver = Observer<ViewStatus> { status ->
        when(status) {
            ViewStatus.LOADING -> loading.show()
            ViewStatus.DONE -> loading.hide()
            ViewStatus.ERROR -> Toast.makeText(DetailActivity@this, R.string.error, Toast.LENGTH_SHORT).show()
            else -> {}
        }
    }

    private val photoDataObserver = Observer<Photo> { photoData ->
        photo.setUrl(imageRequester, photoData.url)
        photoTitle.text = photoData.title
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        imageRequester = ImageRequester(Glide.with(this))
        initViewModel()
    }

    private fun initViewModel() {
        //val repository = LocalRepository(this)
        val repository = RemoteRepository()
        viewModel = ViewModelProviders.of(this, ViewModelFactory(repository)).get(DetailViewModel::class.java)
        viewModel.viewStatus.observe(this, statusObserver)
        viewModel.photoData.observe(this, photoDataObserver)
        viewModel.requestPhoto(intent.getIntExtra(PHOTO_ID, 0))
    }
}