package com.prongbang.androidunittest.feature.feed.data

import com.prongbang.androidunittest.feature.feed.model.Feed

interface FeedRepository {
    suspend fun getFeeds(page: Int): List<Feed>
    suspend fun getFeed(id: Int): Feed?
}

class DefaultFeedRepository(private val feedDataSource: FeedDataSource) : FeedRepository {

    override suspend  fun getFeeds(page: Int): List<Feed> {

        return feedDataSource.findAll(page)
    }

    override suspend fun getFeed(id: Int): Feed? {

        return feedDataSource.findFeed(id)
    }

}