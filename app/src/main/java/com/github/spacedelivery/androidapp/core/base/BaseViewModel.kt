package com.github.spacedelivery.androidapp.core.base

import androidx.lifecycle.ViewModel
import com.github.spacedelivery.androidapp.core.livedata.LiveEvent
import com.github.spacedelivery.androidapp.core.network.Result

open class BaseViewModel : ViewModel() {

    val errorState = LiveEvent<Result.Error>()

    suspend fun <T : Any> checkErrorAndContinue(result: suspend () -> Result<T>): Result<T> =
        result().onError {
            errorState.value = Result.Error(it)
        }


}