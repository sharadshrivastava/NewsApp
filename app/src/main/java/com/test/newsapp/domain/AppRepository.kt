package com.test.newsapp.domain

import com.test.newsapp.data.network.Resource
import com.test.newsapp.domain.model.ApiResponse

interface AppRepository {

    suspend fun newsFeeds(): Resource<ApiResponse?>
}