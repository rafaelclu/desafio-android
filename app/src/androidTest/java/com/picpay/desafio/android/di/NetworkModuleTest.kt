package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.remote.api.RetrofitFactory
import com.picpay.desafio.android.data.remote.api.user.UserService
import org.koin.dsl.module

const val LOCAL_HOST = "http://localhost:3000"

val testNetworkModule = module {

    single {
        RetrofitFactory.build(
            LOCAL_HOST,
            UserService::class.java
        )
    }

}