package com.prongbang.androidunittest.feature.feed.presenter

import androidx.lifecycle.MediatorLiveData
import com.google.common.truth.Truth.assertThat
import com.prongbang.androidunittest.core.Result
import com.prongbang.androidunittest.core.test.ViewModelTest
import com.prongbang.androidunittest.core.livedata.testObserver
import com.prongbang.androidunittest.feature.feed.domain.GetFeedUseCase
import com.prongbang.androidunittest.feature.feed.model.Feed
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

interface FeedViewModelScenario {
    fun getFeed_NotEmpty_Success()
}

@RunWith(JUnit4::class)
class FeedViewModelTest : ViewModelTest(), FeedViewModelScenario {

    @MockK
    lateinit var useCase: GetFeedUseCase

    private val viewModel by lazy { DefaultFeedViewModel(useCase) }

    @Test
    override fun getFeed_NotEmpty_Success() {

        val mockFeed = arrayListOf(Feed(1, "Title 1", "Content 1"))
        val mockResult = MediatorLiveData<Result<List<Feed>>>().apply {
            postValue(Result.Success(mockFeed))
        }
        every { useCase.observe() } returns mockResult

        val actual = viewModel.getFeeds().testObserver().observedValues[0]

        val expected = Result.Success(mockFeed)

        verify { runBlocking { useCase.execute(Unit) } }

        assertThat(actual).isEqualTo(expected)
    }

}