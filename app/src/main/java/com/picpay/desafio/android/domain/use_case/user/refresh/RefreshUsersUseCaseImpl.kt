package com.picpay.desafio.android.domain.use_case.user.refresh

import com.picpay.desafio.android.data.repository.user.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RefreshUsersUseCaseImpl(
    private val userRepository: UserRepository
) : RefreshUsersUseCase {

    override suspend operator fun invoke() = userRepository.refreshUsers()

}