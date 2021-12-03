package com.picpay.desafio.android

import com.picpay.desafio.android.data.local.db.user.entity.UserEntity
import com.picpay.desafio.android.data.remote.api.user.dto.UserResponse
import com.picpay.desafio.android.domain.model.User

object TestDataFactory {

    fun userResponseList() = listOf(
        UserResponse(
            id = 1,
            name = "name1",
            username = "username1",
            img = "url"
        ),
        UserResponse(
            id = 2,
            name = "name2",
            username = "username2",
            img = "url"
        ),
        UserResponse(
            id = 3,
            name = "name3",
            username = "username3",
            img = "url"
        )
    )

    fun userEntityList() = listOf(
        UserEntity(
            id = 1,
            name = "name1",
            username = "username1",
            imageUrl = "url"
        ),
        UserEntity(
            id = 2,
            name = "name2",
            username = "username2",
            imageUrl = "url"
        ),
        UserEntity(
            id = 3,
            name = "name3",
            username = "username3",
            imageUrl = "url"
        )
    )

    fun userList() = listOf(
        User(
            id = 1,
            name = "name1",
            username = "username1",
            imageUrl = "url"
        ),
        User(
            id = 2,
            name = "name2",
            username = "username2",
            imageUrl = "url"
        ),
        User(
            id = 3,
            name = "name3",
            username = "username3",
            imageUrl = "url"
        )
    )

}