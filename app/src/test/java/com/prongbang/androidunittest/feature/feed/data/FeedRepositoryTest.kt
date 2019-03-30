package com.prongbang.androidunittest.feature.feed.data

import com.google.common.truth.Truth.assertThat
import com.prongbang.androidunittest.core.test.RepositoryTest
import com.prongbang.androidunittest.feature.feed.model.Feed
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

interface FeedRepositoryScenario {
    fun getFeed_NotEmpty_FeedList()
    fun getFeed_Id10_Feed()
}

@RunWith(JUnit4::class)
class FeedRepositoryTest : RepositoryTest(), FeedRepositoryScenario {

    @MockK
    private lateinit var feedDataSource: FeedDataSource

    private val feedRepository by lazy { DefaultFeedRepository(feedDataSource) }

    @Test
    override fun getFeed_NotEmpty_FeedList() = runBlocking {

        coEvery { feedDataSource.findAll(1) } returns arrayListOf(Feed(1, "Title 1", "Content 1"))

        val actual = feedRepository.getFeeds(1)

        assertThat(actual[0].id).isEqualTo(1)
        assertThat(actual[0].title).isEqualTo("Title 1")
        assertThat(actual[0].content).isEqualTo("Content 1")
    }

    @Test
    override fun getFeed_Id10_Feed() = runBlocking {

        coEvery { feedDataSource.findFeed(10) } returns Feed(10, "Title 10", "Content 10")

        val actual = feedRepository.getFeed(10)!!

        val expected = Feed(10, "Title 10", "Content 10")

        assertThat(actual.id).isEqualTo(expected.id)
        assertThat(actual.title).isEqualTo(expected.title)
        assertThat(actual.content).isEqualTo(expected.content)
    }

}