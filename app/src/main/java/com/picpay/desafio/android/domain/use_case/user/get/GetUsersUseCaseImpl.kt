package com.picpay.desafio.android.domain.use_case.user.get

import com.picpay.desafio.android.data.repository.user.UserRepository
import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.flow.Flow

class GetUsersUseCaseImpl(
    private val userRepository: UserRepository
) : GetUsersUseCase {

    override operator fun invoke(): Flow<List<User>> = userRepository.getUsers()

}