package com.test.templateapp.data.network

import com.test.templateapp.domain.model.ApiResponse
import com.test.templateapp.domain.model.ToDoItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ToDoApi {

    @GET(ToDo_PATH)
    suspend fun toDos(): ApiResponse?

    @GET("$ToDo_PATH/{id}")  //todos/1
    suspend fun toDoDetails(@Path("id") id : Int): ToDoItem?

    @GET(ToDo_PATH) //todos?id=1
    suspend fun toDoDetailsQuery(@Query("id") id: Int): ApiResponse

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        private const val ToDo_PATH = "todos"
    }
}