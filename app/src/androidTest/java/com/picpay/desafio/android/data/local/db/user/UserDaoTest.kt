package com.picpay.desafio.android.data.local.db.user

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.picpay.desafio.android.TestDataFactory
import com.picpay.desafio.android.data.local.db.PicPayDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@SmallTest
class UserDaoTest {

    private lateinit var database: PicPayDatabase
    private lateinit var dao: UserDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, PicPayDatabase::class.java).build()
        dao = database.userDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun whenInsertMethodIsCalledUsersAreInserted() = runBlocking {
        val users = TestDataFactory.userEntityList()

        dao.insert(users)
        val usersInDatabase = dao.getUsers().first()

        assertEquals(users, usersInDatabase)
    }

}