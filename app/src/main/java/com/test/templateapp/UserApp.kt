package com.test.templateapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class UserApp : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    companion object {
        private var instance: UserApp? = null
        fun get(): UserApp? = instance
    }
}