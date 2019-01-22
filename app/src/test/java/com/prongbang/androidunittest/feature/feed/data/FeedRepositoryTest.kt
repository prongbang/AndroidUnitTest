package com.prongbang.androidunittest.feature.feed.data

import android.content.Context
import com.prongbang.androidunittest.R
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FeedRepositoryTest {

    @Mock
    private lateinit var context: Context

    private val feedRepository by lazy { DefaultFeedRepository(context) }

    @Test
    fun testGetName() {
        Mockito.`when`(context.getString(R.string.app_name)).thenReturn("Hello mock")

        val actual = feedRepository.getAppName()
        Assert.assertThat(actual, CoreMatchers.`is`("Hello mock"))
    }
}