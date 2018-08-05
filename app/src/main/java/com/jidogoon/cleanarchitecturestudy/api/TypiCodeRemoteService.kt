package com.jidogoon.cleanarchitecturestudy.api

import com.jidogoon.cleanarchitecturestudy.data.Photo
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface TypiCodeRemoteService {
    @GET("photos")
    fun getPhotos(): Deferred<List<Photo>>

    @GET("photos/{photo_id}")
    fun getPhoto(@Path("photo_id") photoId: Int):  Deferred<Photo>
}