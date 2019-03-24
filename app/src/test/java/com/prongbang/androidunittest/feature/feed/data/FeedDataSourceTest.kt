package com.prongbang.androidunittest.feature.feed.data

import com.google.common.truth.Truth.assertThat
import com.prongbang.androidunittest.core.test.DataSourceTest
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

interface FeedDataSourceScenario {

    fun getFeed_NotEmpty_FeedList()
}

@RunWith(JUnit4::class)
class FeedDataSourceTest: DataSourceTest() , FeedDataSourceScenario{

    private val dataSource by lazy { DefaultFeedDataSource() }

    @Test
    override fun getFeed_NotEmpty_FeedList() = runBlocking {

        val actual = dataSource.findAll()

        assertThat(actual).hasSize(100)
    }
}