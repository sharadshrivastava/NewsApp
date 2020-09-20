package com.test.templateapp.ui.users

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.test.templateapp.data.AppRepositoryImpl
import com.test.templateapp.data.network.Resource
import com.test.templateapp.domain.model.ApiResponse
import com.test.templateapp.domain.usecases.UserUseCase

class UserListViewModel @ViewModelInject constructor(
    private val useCase: UserUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    fun users() = liveData {
        emit(Resource.loading<ApiResponse>())
        emit(useCase.users())
    }
}