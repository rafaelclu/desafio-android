package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TestApplication : Application() {

    private val modules = listOf(
        viewModelModule, testNetworkModule,
        databaseModule, repositoryModule,
        dataSourceModule, useCaseModule
    )

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@TestApplication)
            modules(modules)
        }
    }

}