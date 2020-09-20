package com.test.templateapp.domain.usecases

import com.test.templateapp.data.AppRepositoryImpl
import javax.inject.Inject

class UserUseCase @Inject constructor(private val repository: AppRepositoryImpl) {

    suspend fun users() = repository.users()

    suspend fun userDetails(id: Int) = repository.userDetails(id)
}