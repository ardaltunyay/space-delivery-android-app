package com.github.spacedelivery.androidapp.core.network

import android.content.Context
import com.github.spacedelivery.androidapp.BuildConfig
import com.github.spacedelivery.androidapp.core.network.interceptors.NetworkConnectionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val READ_TIMEOUT_IN_MILLISECONDS = 15000L
private const val CONNECT_TIMEOUT_IN_MILLISECONDS = 15000L

fun createNetworkConnectionInterceptor(context: Context): NetworkConnectionInterceptor =
    NetworkConnectionInterceptor(context)

fun createHttpLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

fun createOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    networkConnectionInterceptor: NetworkConnectionInterceptor,
): OkHttpClient =
    with(OkHttpClient.Builder()) {
        connectTimeout(CONNECT_TIMEOUT_IN_MILLISECONDS, TimeUnit.MILLISECONDS)
        readTimeout(READ_TIMEOUT_IN_MILLISECONDS, TimeUnit.MILLISECONDS)
        addInterceptor(networkConnectionInterceptor)
        if (BuildConfig.DEBUG) {
            addInterceptor(httpLoggingInterceptor)
        }
        build()
    }

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T =
    with(Retrofit.Builder()) {
        baseUrl(url)
        client(okHttpClient)
        addConverterFactory(GsonConverterFactory.create())
        build()
    }.create(T::class.java)