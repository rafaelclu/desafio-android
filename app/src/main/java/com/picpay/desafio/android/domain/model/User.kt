package com.picpay.desafio.android.domain.model

import com.picpay.desafio.android.data.local.db.user.entity.UserEntity
import com.picpay.desafio.android.presentation.view.main.model.UserModelData

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val imageUrl: String
)

fun User.toUserEntity() = UserEntity(
    id = id,
    name = name,
    imageUrl = imageUrl,
    username = username
)

fun User.toUserModelData() = UserModelData(
    name = name,
    imageUrl = imageUrl,
    username = username
)
