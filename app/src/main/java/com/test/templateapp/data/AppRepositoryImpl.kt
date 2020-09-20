package com.test.templateapp.data

import com.test.templateapp.data.network.Resource
import com.test.templateapp.data.network.ResponseHandler
import com.test.templateapp.data.network.ToDoApi
import com.test.templateapp.domain.AppRepository
import com.test.templateapp.domain.model.ApiResponse
import com.test.templateapp.domain.model.ToDoItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor(private val api: ToDoApi) : AppRepository {

    override suspend fun users(): Resource<ApiResponse?> = try {
        ResponseHandler.handleSuccess(api.toDos())
    } catch (e: Exception) {
        ResponseHandler.handleException(e)
    }

    override suspend fun userDetails(id: Int): Resource<ToDoItem?> = try {
        ResponseHandler.handleSuccess(api.toDoDetails(id))
    } catch (e: Exception) {
        ResponseHandler.handleException(e)
    }
}