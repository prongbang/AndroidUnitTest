package com.prongbang.androidunittest.feature.feed.data

import com.prongbang.androidunittest.feature.feed.model.Feed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface FeedDataSource {
    suspend fun findAll(): List<Feed>
}

class DefaultFeedDataSource : FeedDataSource {

    override suspend fun findAll(): List<Feed> {

        return withContext(Dispatchers.Default) {

            val feeds = arrayListOf<Feed>()
            for (i in 1..100) {
                feeds.add(Feed().apply {
                    id = i
                    title = "Title $i"
                    content = "Content $i"
                })
            }

            return@withContext feeds
        }
    }
}