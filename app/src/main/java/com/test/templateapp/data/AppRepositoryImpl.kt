package com.test.templateapp.data

import com.test.templateapp.data.network.Resource
import com.test.templateapp.data.network.ResponseHandler
import com.test.templateapp.data.network.ToDoApi
import com.test.templateapp.data.network.ToDoApi.Companion.FEED_QUERY_VALUE
import com.test.templateapp.domain.AppRepository
import com.test.templateapp.domain.model.ApiResponse
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