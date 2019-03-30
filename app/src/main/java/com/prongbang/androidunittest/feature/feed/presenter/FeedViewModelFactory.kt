package com.prongbang.androidunittest.feature.feed.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prongbang.androidunittest.feature.feed.domain.GetFeedByIdUseCase
import com.prongbang.androidunittest.feature.feed.domain.GetFeedUseCase

class FeedViewModelFactory(
    private val getAppNameUseCase: GetFeedUseCase,
    private val getFeedByIdUseCase: GetFeedByIdUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        @Suppress("UNCHECKED_CAST")
        return DefaultFeedViewModel(getAppNameUseCase, getFeedByIdUseCase) as T
    }

}