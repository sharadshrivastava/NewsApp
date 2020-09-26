package com.test.templateapp.ui.users

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.test.templateapp.domain.model.Feed
import com.test.templateapp.domain.model.Item
import com.test.templateapp.domain.usecases.NewsUseCase

class NewsViewModel @ViewModelInject constructor(
    private val useCase: NewsUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var clickEvent  = MutableLiveData<Item>()
    var layoutIds: Array<Int>? = null

    var feeds = liveData {
        //emit(Resource.loading<ApiResponse>())
        emit(useCase.newsFeeds())
    }

}