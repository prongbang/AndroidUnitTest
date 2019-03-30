package com.prongbang.androidunittest.feature.feed.domain

import com.google.common.truth.Truth.assertThat
import com.prongbang.androidunittest.core.Result
import com.prongbang.androidunittest.core.livedata.testObserver
import com.prongbang.androidunittest.core.test.UseCaseTest
import com.prongbang.androidunittest.feature.feed.data.FeedRepository
import com.prongbang.androidunittest.feature.feed.model.Feed
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Test

interface GetFeedByIdUseCaseScenario {
    fun getFeedInvoke_Id10_Feed()
    fun getFeedInvoke_Id10_Error()
    fun getFeedExecuteNow_Id10_Feed()
    fun getFeedExecuteNow_Id10_Error()
}

class GetFeedByIdUseCaseTest : UseCaseTest(), GetFeedByIdUseCaseScenario {

    @MockK
    private lateinit var feedRepository: FeedRepository

    private val useCase by lazy { DefaultGetFeedByIdUseCase(feedRepository) }

    @Test
    override fun getFeedInvoke_Id10_Feed() = runBlocking {

        val mockResult = Feed(10, "Title 10", "Content 10")

        coEvery { feedRepository.getFeed(10) } returns mockResult

        val actual = useCase.invoke(10).testObserver().observedValues[0]

        val expected = Result.Success(mockResult)

        assertThat(actual is Result.Success).isTrue()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    override fun getFeedInvoke_Id10_Error() = runBlocking {

        coEvery { feedRepository.getFeed(10) } returns null

        val actual = useCase.invoke(10).testObserver().observedValues[0]

        assertThat(actual is Result.Error).isTrue()
    }

    @Test
    override fun getFeedExecuteNow_Id10_Feed() = runBlocking {

        val mockResult = Feed(10, "Title 10", "Content 10")

        coEvery { feedRepository.getFeed(10) } returns mockResult

        val actual = useCase.executeNow(10)

        val expected = Result.Success(mockResult)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    override fun getFeedExecuteNow_Id10_Error() = runBlocking {

        coEvery { feedRepository.getFeed(10) } returns null

        val actual = useCase.executeNow(10)

        assertThat(actual is Result.Error).isTrue()
    }

}