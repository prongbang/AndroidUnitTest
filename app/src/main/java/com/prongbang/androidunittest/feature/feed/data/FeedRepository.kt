package com.prongbang.androidunittest.feature.feed.data

import com.prongbang.androidunittest.feature.feed.model.Feed

interface FeedRepository {

    suspend fun getFeeds(): List<Feed>

}

class DefaultFeedRepository constructor(private val feedDataSource: FeedDataSource) : FeedRepository {

    override suspend  fun getFeeds(): List<Feed> {

        return feedDataSource.findAll()
    }

}