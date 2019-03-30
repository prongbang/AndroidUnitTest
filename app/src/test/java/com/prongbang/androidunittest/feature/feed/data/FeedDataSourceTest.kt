package com.prongbang.androidunittest.feature.feed.data

import com.google.common.truth.Truth.assertThat
import com.prongbang.androidunittest.core.test.DataSourceTest
import com.prongbang.androidunittest.feature.feed.model.Feed
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

interface FeedDataSourceScenario {

    fun getFeed_NotEmpty_FeedList()
    fun getFeed_Id10_Feed()
}

@RunWith(JUnit4::class)
class FeedDataSourceTest : DataSourceTest(), FeedDataSourceScenario {

    private val dataSource by lazy { DefaultFeedDataSource() }

    @Test
    override fun getFeed_NotEmpty_FeedList() = runBlocking {

        val actual = dataSource.findAll(100)

        assertThat(actual).hasSize(100)
    }

    @Test
    override fun getFeed_Id10_Feed() = runBlocking {

        val actual = dataSource.findFeed(10)

        val expected = Feed(10, "Title 10", "Content 10")

        assertThat(actual.id).isEqualTo(expected.id)
        assertThat(actual.title).isEqualTo(expected.title)
        assertThat(actual.content).isEqualTo(expected.content)
    }

}