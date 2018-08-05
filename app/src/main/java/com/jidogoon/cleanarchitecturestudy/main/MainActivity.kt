package com.jidogoon.cleanarchitecturestudy.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.bumptech.glide.Glide
import com.jidogoon.cleanarchitecturestudy.R
import com.jidogoon.cleanarchitecturestudy.ViewModelFactory
import com.jidogoon.cleanarchitecturestudy.common.ViewStatus
import com.jidogoon.cleanarchitecturestudy.data.Photo
import com.jidogoon.cleanarchitecturestudy.detail.DetailActivity
import com.jidogoon.cleanarchitecturestudy.extension.hide
import com.jidogoon.cleanarchitecturestudy.extension.show
import com.jidogoon.cleanarchitecturestudy.image.ImageRequester
import com.jidogoon.cleanarchitecturestudy.repository.LocalRepository
import com.jidogoon.cleanarchitecturestudy.repository.RemoteRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var photosAdapter: MainAdapter
    private lateinit var viewModel: MainViewModel

    private val statusObserver = Observer<ViewStatus> { status ->
        when(status) {
            ViewStatus.LOADING -> loading.show()
            ViewStatus.DONE -> loading.hide()
            ViewStatus.ERROR -> Toast.makeText(MainActivity@this, R.string.error, Toast.LENGTH_SHORT).show()
            else -> {}
        }
    }

    private val photosObserver = Observer<List<Photo>> { photos ->
        photosAdapter.setItems(photos)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        mainRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 4, OrientationHelper.VERTICAL, false)
            photosAdapter = MainAdapter(ImageRequester(Glide.with(MainActivity@this)), onPhotoClickListener)
            adapter = photosAdapter
        }
    }

    private fun initViewModel() {
        //val repository = LocalRepository(this)
        val repository = RemoteRepository()
        viewModel = ViewModelProviders.of(this, ViewModelFactory(repository)).get(MainViewModel::class.java)
        viewModel.viewStatus.observe(this, statusObserver)
        viewModel.photos.observe(this, photosObserver)
        viewModel.requestPhotos()
    }

    private val onPhotoClickListener = { photo: Photo ->
        DetailActivity.startActivity(MainActivity@this, photo.id)
    }
}
