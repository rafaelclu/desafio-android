package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.local.datasource.user.UserLocalDataSource
import com.picpay.desafio.android.data.local.datasource.user.UserLocalDataSourceImpl
import com.picpay.desafio.android.data.remote.datasource.user.UserRemoteDataSource
import com.picpay.desafio.android.data.remote.datasource.user.UserRemoteDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {

    single<UserLocalDataSource> { UserLocalDataSourceImpl(get()) }
    single<UserRemoteDataSource> { UserRemoteDataSourceImpl(get()) }

}