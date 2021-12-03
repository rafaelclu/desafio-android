package com.picpay.desafio.android.di

import com.picpay.desafio.android.data.repository.user.UserRepositoryImpl
import com.picpay.desafio.android.data.repository.user.UserRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<UserRepository> { UserRepositoryImpl(get(), get()) }

}