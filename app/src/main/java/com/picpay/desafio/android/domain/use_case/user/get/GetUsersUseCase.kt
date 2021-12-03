package com.picpay.desafio.android.domain.use_case.user.get

import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.flow.Flow

interface GetUsersUseCase {

    operator fun invoke(): Flow<List<User>>

}