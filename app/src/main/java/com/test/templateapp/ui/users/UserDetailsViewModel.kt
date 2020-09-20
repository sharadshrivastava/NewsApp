package com.test.templateapp.ui.users

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.test.templateapp.data.network.Resource
import com.test.templateapp.domain.model.ApiResponse
import com.test.templateapp.domain.model.ToDoItem
import com.test.templateapp.domain.usecases.UserUseCase

class UserDetailsViewModel @ViewModelInject constructor(
    private val useCase: UserUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    fun userDetails(id: Int) = liveData {
        emit(Resource.loading<ToDoItem>())
        emit(useCase.userDetails(id))
    }
}