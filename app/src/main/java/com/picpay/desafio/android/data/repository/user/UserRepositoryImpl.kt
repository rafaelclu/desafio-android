package com.picpay.desafio.android.data.repository.user

import com.picpay.desafio.android.data.local.datasource.user.UserLocalDataSource
import com.picpay.desafio.android.data.remote.datasource.user.UserRemoteDataSource
import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) : UserRepository {

    override fun getUsers(): Flow<List<User>> = localDataSource.getUsers()

    override suspend fun refreshUsers() {
        val users = remoteDataSource.getUsers()
        localDataSource.insertUsers(users)
    }

}