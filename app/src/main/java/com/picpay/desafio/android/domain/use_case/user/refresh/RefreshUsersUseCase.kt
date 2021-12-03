package com.picpay.desafio.android.domain.use_case.user.refresh

import com.picpay.desafio.android.data.exception.DatabaseException
import com.picpay.desafio.android.data.exception.NoInternetException
import com.picpay.desafio.android.data.exception.PerformOperationException

interface RefreshUsersUseCase {

    @Throws(NoInternetException::class, DatabaseException::class, PerformOperationException::class)
    suspend operator fun invoke()

}