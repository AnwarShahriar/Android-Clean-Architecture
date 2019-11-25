package com.project.nagad.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.project.nagad.local.database.NagadBazaarDB
import com.project.nagad.local.database.UserInfoDAO
import com.project.nagad.local.utils.TestData
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class UserInfoDAOTest {

    private lateinit var nagadBazaarDB: NagadBazaarDB
    private lateinit var userInfoDAO: UserInfoDAO

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        nagadBazaarDB = Room.inMemoryDatabaseBuilder(context, NagadBazaarDB::class.java)
            .allowMainThreadQueries()
            .build()

        userInfoDAO = nagadBazaarDB.getUserInfoDao()
    }

    @After
    fun tearDown() {
        nagadBazaarDB.close()
    }

    @Test
    fun test_saveAndRetrieveUserInfo() {
        val userInfo = TestData.generateUserInfo()

        userInfoDAO.saveUserInfo(userInfo)

        userInfoDAO.getUserInfo(userInfo.userIdentifier)
            .test()
            .assertValue { it == userInfo }
            .assertNotComplete() // As Room Observables are kept alive
    }

    @Test
    fun test_updateUserInfo() {
        val userInfo = TestData.generateUserInfo()

        userInfoDAO.updateUserInfo(userInfo)
            .test()
            .assertComplete() // As Room Observables are kept alive
    }

    @Test
    fun test_deleteUserInfo() {

        userInfoDAO.deleteUserInfo()
            .test()
            .assertComplete()
    }
}