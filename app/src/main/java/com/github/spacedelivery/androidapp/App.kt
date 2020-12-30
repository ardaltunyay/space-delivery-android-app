package com.github.spacedelivery.androidapp

import android.app.Application
import com.github.spacedelivery.androidapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(applicationContext)
            modules(
                appModule,
                networkModule,
                dataSourceModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}