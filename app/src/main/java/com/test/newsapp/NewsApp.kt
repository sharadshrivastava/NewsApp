package com.test.newsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private var instance: NewsApp? = null
        fun get(): NewsApp? = instance
    }
}