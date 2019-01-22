package com.prongbang.androidunittest.feature.feed.presenter

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.prongbang.androidunittest.core.Result
import com.prongbang.androidunittest.feature.feed.domain.GetAppNameUseCase

class FeedViewModel constructor(
    private val getAppNameUseCase: GetAppNameUseCase
) : ViewModel() {

    fun getAppName(): LiveData<Result<String>> {

        return getAppNameUseCase.invoke("")
    }

    fun getAppNames(): String {

        return getAppNameUseCase.execute("")
    }

}