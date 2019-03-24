package com.prongbang.androidunittest.core.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.prongbang.androidunittest.core.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface AbstractUseCase<in P, R> {

    suspend fun invoke(parameters: P): LiveData<Result<R>>

    fun executeNow(parameters: P): Result<R>

    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    fun execute(parameters: P): R
}

/**
 * Executes business logic synchronously or asynchronously using a [Scheduler].
 */
abstract class UseCase<in P, R> : AbstractUseCase<P, R> {

    /** Executes the use case asynchronously and places the [Result] in a MutableLiveData
     *
     * @param parameters the input parameters to run the use case with
     * @param result the MutableLiveData where the result is posted to
     *
     */
    suspend operator fun invoke(parameters: P, result: MutableLiveData<Result<R>>) {
        result.value = Result.Loading
        try {
            withContext(Dispatchers.IO) {
                try {
                    execute(parameters).let { useCaseResult ->
                        result.postValue(Result.Success(useCaseResult))
                    }
                } catch (e: Exception) {
                    result.postValue(Result.Error(e))
                }
            }
        } catch (e: Exception) {
            result.postValue(Result.Error(e))
        }
    }

    /** Executes the use case asynchronously and returns a [Result] in a new LiveData object.
     *
     * @return an observable [LiveData] with a [Result].
     *
     * @param parameters the input parameters to run the use case with
     */
    override suspend operator fun invoke(parameters: P): LiveData<Result<R>> {
        val liveCallback: MutableLiveData<Result<R>> = MutableLiveData()
        this(parameters, liveCallback)
        return liveCallback
    }

    /** Executes the use case synchronously  */
    override fun executeNow(parameters: P): Result<R> {
        return try {
            Result.Success(execute(parameters))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}

suspend operator fun <R> UseCase<Unit, R>.invoke(): LiveData<Result<R>> = this(Unit)
suspend operator fun <R> UseCase<Unit, R>.invoke(result: MutableLiveData<Result<R>>) = this(Unit, result)