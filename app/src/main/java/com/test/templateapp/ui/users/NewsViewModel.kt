package com.test.templateapp.ui.users

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.test.templateapp.data.network.Resource
import com.test.templateapp.domain.model.ApiResponse
import com.test.templateapp.domain.usecases.NewsUseCase

class NewsViewModel @ViewModelInject constructor(
    private val useCase: NewsUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var feeds = liveData {
        //emit(Resource.loading<ApiResponse>())
        emit(useCase.newsFeeds())
    }
}