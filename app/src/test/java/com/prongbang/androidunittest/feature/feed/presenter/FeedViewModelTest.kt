package com.prongbang.androidunittest.feature.feed.presenter

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.content.Context
import com.prongbang.androidunittest.R
import com.prongbang.androidunittest.core.Result
import com.prongbang.androidunittest.feature.feed.data.DefaultFeedRepository
import com.prongbang.androidunittest.feature.feed.domain.GetAppNameUseCase
import com.prongbang.androidunittest.utils.LiveDataTestUtil
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FeedViewModelTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var context: Context

    private val feedRepository by lazy { DefaultFeedRepository(context) }

    private val useCase by lazy { GetAppNameUseCase(feedRepository) }

    private val viewModel by lazy { FeedViewModel(useCase) }

    @Test
    fun testGetAppName() {

        Mockito.`when`(context.getString(R.string.app_name)).thenReturn("Hello mock")

        val actual = LiveDataTestUtil.getValue(viewModel.getAppName())

        Assert.assertThat(getDataInResult(actual), CoreMatchers.`is`(""))
    }

    @Test
    fun testGetAppNames() {

        Mockito.`when`(context.getString(R.string.app_name)).thenReturn("Hello mock")

        val actual = viewModel.getAppNames()

        Assert.assertThat(actual, CoreMatchers.`is`("Hello mock"))
    }

    private fun getDataInResult(actual: Result<String>?): String? {
        return when (actual) {
            is Result.Success -> {
                actual.data
            }
            else -> {
                null
            }
        }
    }
}