package com.prongbang.androidunittest.feature.feed.presenter

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.prongbang.androidunittest.feature.feed.domain.GetAppNameUseCase

class FeedViewModelFactory(
    private val getAppNameUseCase: GetAppNameUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        @Suppress("UNCHECKED_CAST")
        return FeedViewModel(getAppNameUseCase) as T
    }

}