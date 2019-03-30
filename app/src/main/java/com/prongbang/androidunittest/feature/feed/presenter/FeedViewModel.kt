package com.prongbang.androidunittest.feature.feed.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.prongbang.androidunittest.core.Result
import com.prongbang.androidunittest.core.viewmodel.BaseViewModel
import com.prongbang.androidunittest.feature.feed.domain.GetFeedByIdUseCase
import com.prongbang.androidunittest.feature.feed.domain.GetFeedUseCase
import com.prongbang.androidunittest.feature.feed.model.Feed
import kotlinx.coroutines.launch

abstract class FeedViewModel : BaseViewModel() {
    abstract fun getFeeds(page: Int): LiveData<Result<List<Feed>>>
    abstract fun getFeed(id: Int): LiveData<Result<Feed>>
}

class DefaultFeedViewModel(
    private val getAppNameUseCase: GetFeedUseCase,
    private val getFeedByIdUseCase: GetFeedByIdUseCase
) : FeedViewModel() {

    override fun getFeeds(page: Int): LiveData<Result<List<Feed>>> {

        job = launch {
            getAppNameUseCase.execute(page)
        }

        return getAppNameUseCase.observe()
    }

    override fun getFeed(id: Int): LiveData<Result<Feed>> {

        val data = MediatorLiveData<Result<Feed>>()
        job = launch {
            val source = getFeedByIdUseCase.invoke(id)
            data.addSource(source) {
                data.postValue(it)
            }
        }

        return data
    }
}