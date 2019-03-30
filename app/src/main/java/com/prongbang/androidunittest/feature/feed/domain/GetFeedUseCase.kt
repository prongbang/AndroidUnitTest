package com.prongbang.androidunittest.feature.feed.domain

import androidx.lifecycle.MutableLiveData
import com.prongbang.androidunittest.core.Result
import com.prongbang.androidunittest.core.domain.MediatorUseCase
import com.prongbang.androidunittest.feature.feed.data.FeedRepository
import com.prongbang.androidunittest.feature.feed.model.Feed

abstract class GetFeedUseCase : MediatorUseCase<Int, Result<List<Feed>>>()

class DefaultGetFeedUseCase(private val feedRepository: FeedRepository) : GetFeedUseCase() {

    override suspend fun execute(parameters: Int) {

        addSource(MutableLiveData<Result<List<Feed>>>().apply {
            postValue(Result.Success(feedRepository.getFeeds(parameters)))
        })

    }

}