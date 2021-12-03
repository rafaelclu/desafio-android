package com.picpay.desafio.android.data.local.datasource.user

import android.database.sqlite.SQLiteException
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.TestDataFactory
import com.picpay.desafio.android.data.exception.DatabaseException
import com.picpay.desafio.android.data.exception.PerformOperationException
import com.picpay.desafio.android.data.local.db.user.UserDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class UserLocalDataSourceImplTest {

    private fun localDataSource(
        dao: UserDao = mock()
    ) = UserLocalDataSourceImpl(dao)

    @Test
    fun `when insertUsers is called, correct dao method is called`() = runBlockingTest {
        val userList = TestDataFactory.userList()

        val dao = mock<UserDao>()

        val localDataSource = localDataSource(
            dao = dao
        )

        localDataSource.insertUsers(userList)

        verify(dao).insert(any())
    }

    @Test
    fun `when insertUsers is called with a sqlite exception, DatabaseException is throw`() =
        runBlockingTest {
            val userEntityList = TestDataFactory.userEntityList()
            val userList = TestDataFactory.userList()

            val dao = mock<UserDao>()
            whenever(dao.insert(userEntityList)).thenThrow(SQLiteException())

            val localDataSource = localDataSource(
                dao = dao
            )

            Assert.assertThrows(DatabaseException::class.java) {
                runBlockingTest {
                    localDataSource.insertUsers(userList)
                }
            }
        }

    @Test
    fun `when getUsers is called, correct dao method is called`() = runBlockingTest {
        val dao = mock<UserDao>()

        val localDataSource = localDataSource(
            dao = dao
        )

        localDataSource.getUsers()

        verify(dao).getUsers()
    }

    @Test
    fun `when getUsers is called, it returns all the users that are in database`() =
        runBlockingTest {
            val userEntityList = TestDataFactory.userEntityList()

            val dao = mock<UserDao>()
            whenever(dao.getUsers()).thenReturn(flow { emit(userEntityList) })

            val localDataSource = localDataSource(
                dao = dao
            )

            val users = localDataSource.getUsers().first()

            assertEquals(3, users.size)
        }

}