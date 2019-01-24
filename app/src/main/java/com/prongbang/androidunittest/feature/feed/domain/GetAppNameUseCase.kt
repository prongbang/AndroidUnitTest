package com.prongbang.androidunittest.feature.feed.domain

import com.prongbang.androidunittest.core.UseCase
import com.prongbang.androidunittest.feature.feed.data.FeedRepository

abstract class GetAppNameUseCase : UseCase<String, String>()

class DefaultGetAppNameUseCase constructor(
    private val feedRepository: FeedRepository
) : GetAppNameUseCase() {

    override fun execute(parameters: String): String {

        return feedRepository.getAppName()
    }
}