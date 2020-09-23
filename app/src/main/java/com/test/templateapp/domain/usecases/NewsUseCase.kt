package com.test.templateapp.domain.usecases

import com.test.templateapp.data.AppRepositoryImpl
import javax.inject.Inject

class NewsUseCase @Inject constructor(private val repository: AppRepositoryImpl) {

    suspend fun newsFeeds() = repository.newsFeeds()
}