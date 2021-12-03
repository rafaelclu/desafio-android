package com.picpay.desafio.android.data.local.datasource.user

import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {

    fun getUsers(): Flow<List<User>>
    suspend fun insertUsers(userList: List<User>)

}