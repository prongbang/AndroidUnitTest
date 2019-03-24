package com.prongbang.androidunittest.feature.feed.domain

import com.google.common.truth.Truth.assertThat
import com.prongbang.androidunittest.core.Result
import com.prongbang.androidunittest.core.test.UseCaseTest
import com.prongbang.androidunittest.core.livedata.testObserver
import com.prongbang.androidunittest.feature.feed.data.FeedRepository
import com.prongbang.androidunittest.feature.feed.model.Feed
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

interface GetFeedUseCaseScenario {
    fun getFeed_NotEmpty_Success()
}

@RunWith(JUnit4::class)
class GetFeedUseCaseTest : UseCaseTest(), GetFeedUseCaseScenario {

    @MockK
    private lateinit var feedRepository: FeedRepository

    private val useCase by lazy { DefaultGetFeedUseCase(feedRepository) }

    @Test
    override fun getFeed_NotEmpty_Success() = runBlocking {

        val mockResponse = arrayListOf(Feed(1, "Title 1", "Content 1"))
        coEvery { feedRepository.getFeeds() } returns mockResponse

        useCase.execute(Unit)

        val actual = useCase.observe().testObserver().observedValues[0]

        val expected = Result.Success(mockResponse)

        assertThat(actual).isEqualTo(expected)

    }

}