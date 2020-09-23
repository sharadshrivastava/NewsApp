package com.test.templateapp.domain

import com.test.templateapp.data.network.Resource
import com.test.templateapp.domain.model.ApiResponse

interface AppRepository {

    suspend fun newsFeeds(): Resource<ApiResponse?>
}