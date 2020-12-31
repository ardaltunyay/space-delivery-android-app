package com.github.spacedelivery.androidapp.core.network.interceptors

import android.content.Context
import com.github.spacedelivery.androidapp.R
import com.github.spacedelivery.androidapp.core.exceptions.NoInternetException
import com.github.spacedelivery.androidapp.core.extensions.isInternetAvailable
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(private val context: Context) : Interceptor {

    private val applicationContext
        get() = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!applicationContext.isInternetAvailable) {
            throw NoInternetException(msg = context.getString(R.string.error_internet_connection))
        }
        return chain.proceed(chain.request())
    }
}