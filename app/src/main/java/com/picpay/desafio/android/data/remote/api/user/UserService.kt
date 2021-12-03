package com.picpay.desafio.android.data.remote.api.user

import com.picpay.desafio.android.data.remote.api.user.dto.UserResponse
import retrofit2.http.GET


interface UserService {

    @GET("users")
    suspend fun getUsers(): List<UserResponse>

}