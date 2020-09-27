package com.test.newsapp.di

import com.test.newsapp.data.network.ToDoApi
import com.test.newsapp.data.network.ToDoApi.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
object ServiceModule {

    @Provides
    fun provideUserApi(): ToDoApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ToDoApi::class.java)
    }
}