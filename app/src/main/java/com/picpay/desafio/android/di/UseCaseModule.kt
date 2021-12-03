package com.picpay.desafio.android.di

import com.picpay.desafio.android.domain.use_case.user.get.GetUsersUseCase
import com.picpay.desafio.android.domain.use_case.user.get.GetUsersUseCaseImpl
import com.picpay.desafio.android.domain.use_case.user.refresh.RefreshUsersUseCase
import com.picpay.desafio.android.domain.use_case.user.refresh.RefreshUsersUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {

    single<GetUsersUseCase> { GetUsersUseCaseImpl(get()) }
    single<RefreshUsersUseCase> { RefreshUsersUseCaseImpl(get()) }

}