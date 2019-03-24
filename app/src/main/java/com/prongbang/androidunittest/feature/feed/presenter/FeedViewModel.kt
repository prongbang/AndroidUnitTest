package com.prongbang.androidunittest.feature.feed.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.prongbang.androidunittest.core.Result
import com.prongbang.androidunittest.feature.feed.domain.GetFeedUseCase
import com.prongbang.androidunittest.feature.feed.model.Feed
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

abstract class FeedViewModel : ViewModel() {
   abstract fun getFeeds(): LiveData<Result<List<Feed>>>
}

class DefaultFeedViewModel(
    private val getAppNameUseCase: GetFeedUseCase
) : FeedViewModel() {

    override fun getFeeds(): LiveData<Result<List<Feed>>> {

        GlobalScope.launch {
            getAppNameUseCase.execute(Unit)
        }

        return getAppNameUseCase.observe()
    }

}