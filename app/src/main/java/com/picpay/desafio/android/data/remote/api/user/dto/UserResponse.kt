package com.picpay.desafio.android.data.remote.api.user.dto

import com.google.gson.annotations.SerializedName
import com.picpay.desafio.android.domain.model.User

data class UserResponse(
    @SerializedName("img") val img: String,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String
)

fun UserResponse.toUser() = User(
    imageUrl = img,
    name = name,
    id = id,
    username = username
)
