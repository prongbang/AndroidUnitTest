package com.prongbang.androidunittest.core.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

/**
 * Executes business logic in its execute method and keep posting updates to the result as
 * [Result<R>].
 * Handling an exception (emit [Results.ServerError] to the result) is the subclasses's responsibility.
 */
abstract class MediatorUseCase<in P, R> {
    protected val result = MediatorLiveData<R>()

    // Make this as open so that mock instances can mock this method
    open fun observe(): MediatorLiveData<R> {
        return result
    }

    open fun addSource(liveData: LiveData<R>) {
        result.removeSource(liveData)
        result.addSource(liveData) {
            result.postValue(it)
        }
    }

    abstract suspend fun execute(parameters: P)
}