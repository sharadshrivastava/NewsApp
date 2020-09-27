package com.test.newsapp.domain.model

data class ApiResponse(
    val feed: Feed?,
    val items: List<Item>?,
    val status: String?
)