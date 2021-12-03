package com.picpay.desafio.android.data.repository.user

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.TestDataFactory
import com.picpay.desafio.android.data.exception.NoInternetException
import com.picpay.desafio.android.data.exception.PerformOperationException
import com.picpay.desafio.android.data.local.datasource.user.UserLocalDataSource
import com.picpay.desafio.android.data.remote.datasource.user.UserRemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class UserRepositoryImplTest {

    private fun repository(
        remoteDataSource: UserRemoteDataSource = mock(),
        localDataSource: UserLocalDataSource = mock()
    ) = UserRepositoryImpl(
        remoteDataSource = remoteDataSource,
        localDataSource = localDataSource
    )

    @Test
    fun `when getUsers is called, it returns all the users from local data source`() =
        runBlockingTest {
            val userList = TestDataFactory.userList()

            val localDataSource = mock<UserLocalDataSource>()
            whenever(localDataSource.getUsers()).thenReturn(flow { emit(userList) })

            val repository = repository(
                localDataSource = localDataSource
            )

            val users = repository.getUsers().first()

            assertEquals(3, users.size)
            assertEquals(userList, users)
        }

    @Test
    fun `when refreshUsers is called, correct localDataSource and remoteDataSource method is called`() =
        runBlockingTest {
            val userList = TestDataFactory.userList()

            val localDataSource = mock<UserLocalDataSource>()
            val remoteDataSource = mock<UserRemoteDataSource>()
            whenever(remoteDataSource.getUsers()).thenReturn(userList)

            val repository = repository(
                localDataSource = localDataSource,
                remoteDataSource = remoteDataSource
            )

            repository.refreshUsers()

            verify(remoteDataSource).getUsers()
            verify(localDataSource).insertUsers(userList)
        }

    @Test
    fun `when refreshUsers is called without internet, it throws an NoInternetException`() =
        runBlockingTest {

            val localDataSource = mock<UserLocalDataSource>()
            val remoteDataSource = mock<UserRemoteDataSource>()
            whenever(remoteDataSource.getUsers()).thenThrow(NoInternetException::class.java)

            val repository = repository(
                localDataSource = localDataSource,
                remoteDataSource = remoteDataSource
            )

            Assert.assertThrows(NoInternetException::class.java) {
                runBlockingTest {
                    repository.refreshUsers()
                }
            }
        }

    @Test
    fun `when refreshUsers is called and fails to fetch users, it throws an PerformOperationException`() =
        runBlockingTest {

            val localDataSource = mock<UserLocalDataSource>()
            val remoteDataSource = mock<UserRemoteDataSource>()
            whenever(remoteDataSource.getUsers()).thenThrow(PerformOperationException::class.java)

            val repository = repository(
                localDataSource = localDataSource,
                remoteDataSource = remoteDataSource
            )

            Assert.assertThrows(PerformOperationException::class.java) {
                runBlockingTest {
                    repository.refreshUsers()
                }
            }
        }

}