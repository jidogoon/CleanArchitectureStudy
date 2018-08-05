package com.jidogoon.cleanarchitecturestudy

import android.app.Application
import com.jidogoon.cleanarchitecturestudy.api.TypiCodeApi

class SampleApp: Application() {
    override fun onCreate() {
        super.onCreate()
        TypiCodeApi.createApi()
    }
}