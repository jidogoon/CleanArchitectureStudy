package com.jidogoon.cleanarchitecturestudy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jidogoon.cleanarchitecturestudy.main.MainViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class MainViewModelTest {
    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel(MockRepository())
    }

    @Test
    fun initialized() {
        assertTrue(::viewModel.isInitialized)
        assertNotNull(viewModel.photos)
    }

    @Test
    fun updateNearby() {
        viewModel.requestPhotos()
        assertNotNull(viewModel.photos.value)
        assertTrue(viewModel.photos.value!!.isNotEmpty())
    }
}