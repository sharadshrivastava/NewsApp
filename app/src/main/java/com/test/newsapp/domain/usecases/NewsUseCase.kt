package com.test.newsapp.domain.usecases

import com.test.newsapp.data.AppRepositoryImpl
import javax.inject.Inject

class NewsUseCase @Inject constructor(private val repository: AppRepositoryImpl) {

    suspend fun newsFeeds() = repository.newsFeeds()
}