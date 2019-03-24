package com.prongbang.androidunittest.feature.feed.domain

import com.prongbang.androidunittest.core.Result
import com.prongbang.androidunittest.core.domain.MediatorUseCase
import com.prongbang.androidunittest.feature.feed.data.FeedRepository
import com.prongbang.androidunittest.feature.feed.model.Feed

abstract class GetFeedUseCase : MediatorUseCase<Unit, Result<List<Feed>>>()

class DefaultGetFeedUseCase constructor(
    private val feedRepository: FeedRepository
) : GetFeedUseCase() {

    override suspend fun execute(parameters: Unit) {

        observe().postValue(Result.Success(feedRepository.getFeeds()))
    }

}