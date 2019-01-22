package com.prongbang.androidunittest.feature.feed.domain

import com.prongbang.androidunittest.core.UseCase
import com.prongbang.androidunittest.feature.feed.data.DefaultFeedRepository

class GetAppNameUseCase constructor(
    private val feedRepository: DefaultFeedRepository
)  : UseCase<String, String>() {

    override fun execute(parameters: String): String {

        return feedRepository.getAppName()
    }
}