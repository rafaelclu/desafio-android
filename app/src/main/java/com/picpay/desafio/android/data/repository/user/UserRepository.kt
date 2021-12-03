package com.picpay.desafio.android.data.repository.user

import com.picpay.desafio.android.data.exception.NoInternetException
import com.picpay.desafio.android.data.exception.PerformOperationException
import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(): Flow<List<User>>

    @Throws(NoInternetException::class, PerformOperationException::class)
    suspend fun refreshUsers()

}