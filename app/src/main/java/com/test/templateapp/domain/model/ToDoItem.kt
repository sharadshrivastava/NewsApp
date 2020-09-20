package com.test.templateapp.domain.model

data class ToDoItem(
    val completed: Boolean?,
    val id: Int?,
    val title: String?,
    val userId: Int?
)