package com.sanathcoding.sglivetrafficimage.login_feature.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test

@SmallTest
class UserCredentialDataBaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao: UserCredentialDao
    private lateinit var dataBase: UserCredentialDataBase

    @Before
    fun setUp() {

        dataBase = Room
            .inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                UserCredentialDataBase::class.java
            )
            .allowMainThreadQueries()
            .build()

        dao = dataBase.dao
    }

    @After
    fun tearDown() {
        dataBase.close()
    }

    @Test
    fun insertUserDataList() = runBlocking {
        val user1 = UserCredentialEntity("bob", "1qaz!QAZ", 1)
        val user2 = UserCredentialEntity("bob1", "1qaz!QAZ", 2)
        val user3 = UserCredentialEntity("bob2", "1qaz!QAZ", 3)

        val userListFromMock = listOf(user1, user2, user3)

        dao.insertUser(user1)
        dao.insertUser(user2)
        dao.insertUser(user3)

        val userList = dao.getUser()

        assertEquals(userListFromMock, userList)

    }

    @Test
    fun doseUserExist() {

        val userExist = dao.doseUserExist("bob")
        assertEquals(userExist, 1)

    }

}