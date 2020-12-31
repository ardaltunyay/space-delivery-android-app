package com.github.spacedelivery.androidapp.di

import com.github.spacedelivery.androidapp.BuildConfig
import com.github.spacedelivery.androidapp.core.network.createHttpLoggingInterceptor
import com.github.spacedelivery.androidapp.core.network.createNetworkConnectionInterceptor
import com.github.spacedelivery.androidapp.core.network.createOkHttpClient
import com.github.spacedelivery.androidapp.core.network.createWebService
import com.github.spacedelivery.androidapp.data.remote.api.ISpaceStationApi
import org.koin.dsl.module

val networkModule = module {
    single { createHttpLoggingInterceptor() }
    single { createNetworkConnectionInterceptor(context = get()) }
    single {
        createOkHttpClient(
            httpLoggingInterceptor = get(),
            networkConnectionInterceptor = get()
        )
    }
    single { createWebService<ISpaceStationApi>(okHttpClient = get(), url = BuildConfig.BASE_URL) }
}