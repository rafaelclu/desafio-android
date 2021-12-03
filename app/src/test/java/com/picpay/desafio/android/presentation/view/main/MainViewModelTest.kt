package com.picpay.desafio.android.presentation.view.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.picpay.desafio.android.TestDataFactory
import com.picpay.desafio.android.data.exception.NoInternetException
import com.picpay.desafio.android.data.exception.PerformOperationException
import com.picpay.desafio.android.domain.use_case.user.get.GetUsersUseCase
import com.picpay.desafio.android.domain.use_case.user.refresh.RefreshUsersUseCase
import com.picpay.desafio.android.utils.MainCoroutineRule
import com.picpay.desafio.android.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private fun viewModel(
        getUsersUseCase: GetUsersUseCase = mock(),
        refreshUsersUseCase: RefreshUsersUseCase = mock()
    ) = MainViewModel(
        getUsersUseCase = getUsersUseCase,
        refreshUsersUseCase = refreshUsersUseCase
    )


    @Test
    fun `when refreshUsers is called, it return the users from database`() = runBlockingTest {
        val userList = TestDataFactory.userList()

        val refreshUsersUseCase = mock<RefreshUsersUseCase>()
        val getUsersUseCase = mock<GetUsersUseCase> {
            onBlocking { invoke() }.thenReturn(flow { emit(userList) })
        }

        val vm = viewModel(
            refreshUsersUseCase = refreshUsersUseCase,
            getUsersUseCase = getUsersUseCase
        )

        vm.refreshUsers()

        assertEquals(3, vm.modelData.getOrAwaitValue().size)
    }

    @Test
    fun `when refreshUsers is called without internet, it emit NO_INTERNET error`() =
        runBlockingTest {

            val refreshUsersUseCase = mock<RefreshUsersUseCase>() {
                onBlocking { invoke() }.thenThrow(NoInternetException::class.java)
            }

            val vm = viewModel(
                refreshUsersUseCase = refreshUsersUseCase
            )

            vm.refreshUsers()

            assertEquals(UserListError.NO_INTERNET, vm.error.getOrAwaitValue().peekContent())
        }

    @Test
    fun `when refreshUsers is called and fails to fetch users, it emit UNKNOWN error`() =
        runBlockingTest {

            val refreshUsersUseCase = mock<RefreshUsersUseCase>() {
                onBlocking { invoke() }.thenThrow(PerformOperationException::class.java)
            }

            val vm = viewModel(
                refreshUsersUseCase = refreshUsersUseCase
            )

            vm.refreshUsers()

            assertEquals(UserListError.UNKNOWN, vm.error.getOrAwaitValue().peekContent())
        }

}