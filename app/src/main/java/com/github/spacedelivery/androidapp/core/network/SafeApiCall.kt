package com.github.spacedelivery.androidapp.core.network

import com.github.spacedelivery.androidapp.core.exceptions.ClientException
import com.github.spacedelivery.androidapp.core.exceptions.ServerException
import retrofit2.Response
import java.io.IOException

suspend fun <T : Any> safeApiCall(apiCall: suspend () -> Response<T>): Result<T> =
    try {
        val response = apiCall()

        if (response.isSuccessful) {
            Result.Success(response.body())
        } else {
            
            val exception = when (response.code()) {
                401 -> {
                    ClientException()
                }
                in 500..599 -> {
                    ServerException()
                }
                else -> {
                    IOException()
                }
            }
            Result.Error(exception)
        }
    } catch (ex: Exception) {
        Result.Error(exception = ex)
    }