package com.test.newsapp.data

import com.test.newsapp.data.network.Resource
import com.test.newsapp.data.network.ResponseHandler
import com.test.newsapp.data.network.ToDoApi
import com.test.newsapp.data.network.ToDoApi.Companion.FEED_QUERY_VALUE
import com.test.newsapp.domain.AppRepository
import com.test.newsapp.domain.model.ApiResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor(private val api: ToDoApi) : AppRepository {

    override suspend fun newsFeeds(): Resource<ApiResponse?> = try {
        ResponseHandler.handleSuccess(api.newsFeed(FEED_QUERY_VALUE))
    } catch (e: Exception) {
        ResponseHandler.handleException(e)
    }
}