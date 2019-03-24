package com.prongbang.androidunittest.feature.feed.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prongbang.androidunittest.feature.feed.domain.GetFeedUseCase

class FeedViewModelFactory(
    private val getAppNameUseCase: GetFeedUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        @Suppress("UNCHECKED_CAST")
        return DefaultFeedViewModel(getAppNameUseCase) as T
    }

}