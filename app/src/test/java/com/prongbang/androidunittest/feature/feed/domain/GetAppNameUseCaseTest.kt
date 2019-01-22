package com.prongbang.androidunittest.feature.feed.domain

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.content.Context
import com.prongbang.androidunittest.R
import com.prongbang.androidunittest.feature.feed.data.DefaultFeedRepository
import com.prongbang.androidunittest.feature.feed.data.FeedRepository
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetAppNameUseCaseTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var context: Context

    private val feedRepository by lazy { DefaultFeedRepository(context) }

    private val useCase by lazy { GetAppNameUseCase(feedRepository) }

    @Test
    fun testGetAppName() {

        Mockito.`when`(context.getString(R.string.app_name)).thenReturn("Hello mock")

        val actual = useCase.execute("")

        Assert.assertThat(actual, CoreMatchers.`is`("Hello mock"))
    }
}