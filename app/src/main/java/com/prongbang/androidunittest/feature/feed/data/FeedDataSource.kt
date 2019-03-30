package com.prongbang.androidunittest.feature.feed.data

import com.prongbang.androidunittest.feature.feed.model.Feed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface FeedDataSource {
    suspend fun findAll(page: Int): List<Feed>
    suspend fun findFeed(id: Int): Feed
}

class DefaultFeedDataSource : FeedDataSource {

    override suspend fun findAll(page: Int): List<Feed> {

        return withContext(Dispatchers.Default) {

            val feeds = arrayListOf<Feed>()
            var i = 1
            while (i <= page) {
                feeds.add(Feed().apply {
                    id = i
                    title = "Title $i"
                    content = "Content $i"
                })
                i++
            }

            return@withContext feeds
        }
    }

    override suspend fun findFeed(id: Int): Feed {

        return withContext(Dispatchers.Default) {
            return@withContext Feed(id = id, title = "Title $id", content = "Content $id")
        }
    }

}