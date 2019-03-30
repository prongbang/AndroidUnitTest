package com.prongbang.androidunittest.core.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {

    protected var job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + SupervisorJob(job)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}