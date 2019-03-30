package com.prongbang.androidunittest.feature.feed.presenter

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth.assertThat
import com.prongbang.androidunittest.core.Result
import com.prongbang.androidunittest.core.livedata.testObserver
import com.prongbang.androidunittest.core.test.ViewModelTest
import com.prongbang.androidunittest.feature.feed.domain.GetFeedByIdUseCase
import com.prongbang.androidunittest.feature.feed.domain.GetFeedUseCase
import com.prongbang.androidunittest.feature.feed.model.Feed
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

interface FeedViewModelScenario {
    fun getFeed_NotEmpty_Success()
    fun getFeed_Id10_Success()
}

@RunWith(JUnit4::class)
class FeedViewModelTest : ViewModelTest(), FeedViewModelScenario {

    @MockK
    lateinit var getFeedUseCase: GetFeedUseCase

    @MockK
    lateinit var getFeedByIdUseCase: GetFeedByIdUseCase

    private val viewModel by lazy { DefaultFeedViewModel(getFeedUseCase, getFeedByIdUseCase) }

    @Test
    override fun getFeed_NotEmpty_Success() = runBlocking {

        val mockFeed = arrayListOf(Feed(1, "Title 1", "Content 1"))

        val mockResult = MediatorLiveData<Result<List<Feed>>>().apply {
            postValue(Result.Success(mockFeed))
        }

        every { getFeedUseCase.observe() } returns mockResult

        val actual = viewModel.getFeeds(1).testObserver().observedValues[0]

        val expected = Result.Success(mockFeed)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    override fun getFeed_Id10_Success() {

        val data = MutableLiveData<Result<Feed>>().apply {
            postValue(Result.Success(Feed(1, "Title 1", "Content 1")))
        }

        coEvery { getFeedByIdUseCase.invoke(1) } returns data

        viewModel.getFeed(1)

        verify { runBlocking { getFeedByIdUseCase.invoke(1) } }
    }

}