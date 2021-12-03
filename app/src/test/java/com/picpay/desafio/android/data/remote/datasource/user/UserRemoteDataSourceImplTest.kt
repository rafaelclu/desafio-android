package com.picpay.desafio.android.data.remote.datasource.user

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.TestDataFactory
import com.picpay.desafio.android.data.exception.PerformOperationException
import com.picpay.desafio.android.data.remote.api.user.UserService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertThrows
import org.junit.Test
import java.net.UnknownHostException
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class UserRemoteDataSourceImplTest {

    private fun remoteDataSource(
        api: UserService = mock(),
    ) = UserRemoteDataSourceImpl(api)

    @Test
    fun `when getUsers is called, correct api method is called`() = runBlockingTest {
        val userResponseList = TestDataFactory.userResponseList()

        val api = mock<UserService>()
        whenever(api.getUsers()).thenReturn(userResponseList)

        val remoteDataSource = remoteDataSource(
            api = api
        )

        remoteDataSource.getUsers()

        verify(api).getUsers()
    }

    @Test
    fun `when getUsers is called, it returns all the users that came in the response`() =
        runBlockingTest {
            val userResponseList = TestDataFactory.userResponseList()

            val api = mock<UserService>()
            whenever(api.getUsers()).thenReturn(userResponseList)

            val remoteDataSource = remoteDataSource(
                api = api
            )

            val users = remoteDataSource.getUsers()

            assertEquals(3, users.size)
        }

    @Test
    fun `when getUsers is called and fails to fetch users, it throws an PerformOperationException`() =
        runBlockingTest {

            val api = mock<UserService>()
            whenever(api.getUsers()).thenThrow(RuntimeException::class.java)

            val remoteDataSource = remoteDataSource(
                api = api
            )

            assertThrows(PerformOperationException::class.java) {
                runBlockingTest {
                    remoteDataSource.getUsers()
                }
            }
        }

}