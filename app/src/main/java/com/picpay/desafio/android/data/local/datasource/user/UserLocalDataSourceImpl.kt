package com.picpay.desafio.android.data.local.datasource.user

import android.database.sqlite.SQLiteException
import android.util.Log
import com.picpay.desafio.android.data.exception.DatabaseException
import com.picpay.desafio.android.data.exception.PerformOperationException
import com.picpay.desafio.android.data.local.db.user.UserDao
import com.picpay.desafio.android.data.local.db.user.entity.toUser
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.model.toUserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.Exception

class UserLocalDataSourceImpl(
    private val userDao: UserDao
) : UserLocalDataSource {

    override fun getUsers(): Flow<List<User>> =
        userDao.getUsers().map { userEntityList ->
            userEntityList.map {
                it.toUser()
            }
        }

    override suspend fun insertUsers(userList: List<User>) {
        try {
            val userEntityList = userList.map { it.toUserEntity() }

            userDao.insert(userEntityList)
        } catch (e: SQLiteException) {
            throw DatabaseException()
        } catch (e: Exception) {
            throw PerformOperationException()
        }
    }


}