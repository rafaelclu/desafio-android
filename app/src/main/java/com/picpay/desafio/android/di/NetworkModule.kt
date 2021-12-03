package com.picpay.desafio.android.di

import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.data.remote.api.RetrofitFactory
import com.picpay.desafio.android.data.remote.api.user.UserService
import org.koin.dsl.module

val networkModule = module {

    single {
        RetrofitFactory.build(
            BuildConfig.API_ENDPOINT,
            UserService::class.java
        )
    }

}