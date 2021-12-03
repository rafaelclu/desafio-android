package com.picpay.desafio.android.data.remote.datasource.user

import com.picpay.desafio.android.data.exception.NoInternetException
import com.picpay.desafio.android.data.exception.PerformOperationException
import com.picpay.desafio.android.data.extension.isConnectionException
import com.picpay.desafio.android.data.remote.api.user.UserService
import com.picpay.desafio.android.data.remote.api.user.dto.toUser
import com.picpay.desafio.android.domain.model.User

class UserRemoteDataSourceImpl(
    private val userService: UserService
) : UserRemoteDataSource {

    override suspend fun getUsers(): List<User> =
        try {
            val response = userService.getUsers()
            response.map { it.toUser() }
        } catch (e: Exception) {
            if (e.isConnectionException()) {
                throw NoInternetException()
            }

            throw PerformOperationException()
        }


}