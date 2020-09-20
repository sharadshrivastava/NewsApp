package com.test.templateapp.domain

import com.test.templateapp.data.network.Resource
import com.test.templateapp.domain.model.ApiResponse
import com.test.templateapp.domain.model.ToDoItem

interface AppRepository {

    suspend fun users(): Resource<ApiResponse?>

    suspend fun userDetails(id: Int): Resource<ToDoItem?>
}