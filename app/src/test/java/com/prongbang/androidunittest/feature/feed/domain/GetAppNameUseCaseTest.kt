package com.prongbang.androidunittest.feature.feed.domain

import com.prongbang.androidunittest.core.UseCaseTest
import com.prongbang.androidunittest.feature.feed.data.FeedRepository
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetAppNameUseCaseTest : UseCaseTest() {

    @Mock
    private lateinit var feedRepository: FeedRepository

    private val useCase by lazy { DefaultGetAppNameUseCase(feedRepository) }

    @Test
    fun testGetAppName() {

        Mockito.`when`(feedRepository.getAppName()).thenReturn("Hello mock")

        val actual = useCase.execute("")

        Assert.assertThat(actual, CoreMatchers.`is`("Hello mock"))
    }
}