package com.prongbang.androidunittest.feature.feed.domain

import com.prongbang.androidunittest.core.domain.UseCase
import com.prongbang.androidunittest.feature.feed.data.FeedRepository
import com.prongbang.androidunittest.feature.feed.model.Feed

abstract class GetFeedByIdUseCase : UseCase<Int, Feed>()

class DefaultGetFeedByIdUseCase(
    private val feedRepository: FeedRepository
) : GetFeedByIdUseCase() {

    override suspend fun execute(parameters: Int): Feed {

        return feedRepository.getFeed(parameters)!!
    }
}