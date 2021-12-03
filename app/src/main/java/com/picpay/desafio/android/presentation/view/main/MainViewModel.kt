package com.picpay.desafio.android.presentation.view.main

import androidx.lifecycle.*
import com.picpay.desafio.android.data.exception.DatabaseException
import com.picpay.desafio.android.data.exception.NoInternetException
import com.picpay.desafio.android.data.exception.PerformOperationException
import com.picpay.desafio.android.domain.model.toUserModelData
import com.picpay.desafio.android.domain.use_case.user.get.GetUsersUseCase
import com.picpay.desafio.android.domain.use_case.user.refresh.RefreshUsersUseCase
import com.picpay.desafio.android.presentation.util.Event
import com.picpay.desafio.android.presentation.view.main.model.UserModelData
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel(
    getUsersUseCase: GetUsersUseCase,
    private val refreshUsersUseCase: RefreshUsersUseCase
) : ViewModel() {

    val modelData: LiveData<List<UserModelData>> = getUsersUseCase().map { users ->
        users.map { it.toUserModelData() }
    }.asLiveData()

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<Event<UserListError>>()
    val error: LiveData<Event<UserListError>> = _error

    fun refreshUsers() = viewModelScope.launch {
        _loading.value = true
        try {
            refreshUsersUseCase()
        } catch (e: NoInternetException) {
            _error.value = Event(UserListError.NO_INTERNET)
        } catch (e: DatabaseException) {
            _error.value = Event(UserListError.UNKNOWN)
        } catch (e: PerformOperationException) {
            _error.value = Event(UserListError.UNKNOWN)
        } finally {
            _loading.value = false
        }
    }

}

enum class UserListError {
    NO_INTERNET, UNKNOWN
}