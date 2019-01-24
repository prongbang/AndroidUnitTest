package com.prongbang.androidunittest.feature.feed.presenter

import android.arch.lifecycle.MutableLiveData
import com.prongbang.androidunittest.core.Result
import com.prongbang.androidunittest.core.ViewModelTest
import com.prongbang.androidunittest.feature.feed.domain.GetAppNameUseCase
import com.prongbang.androidunittest.utils.LiveDataTestUtil
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FeedViewModelTest : ViewModelTest() {

    @Mock
    lateinit var useCase: GetAppNameUseCase

    private val viewModel by lazy { FeedViewModel(useCase) }

    @Test
    fun testGetAppName() {

        val result = MutableLiveData<Result<String>>()
        result.postValue(Result.Success("Hello mock"))
        Mockito.`when`(useCase.invoke("")).thenReturn(result)

        val actual = LiveDataTestUtil.getValue(viewModel.getAppName())

        Assert.assertThat(getDataInResult(actual), CoreMatchers.`is`("Hello mock"))
    }

    @Test
    fun testGetAppNames() {

        Mockito.`when`(useCase.execute("")).thenReturn("Hello mock")

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