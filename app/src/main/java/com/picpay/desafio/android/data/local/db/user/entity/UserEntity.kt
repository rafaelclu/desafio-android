package com.picpay.desafio.android.data.local.db.user.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.picpay.desafio.android.domain.model.User

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "image_url")
    val imageUrl: String
)

fun UserEntity.toUser() = User(
        id = id,
        name = name,
        imageUrl = imageUrl,
        username = username
    )
