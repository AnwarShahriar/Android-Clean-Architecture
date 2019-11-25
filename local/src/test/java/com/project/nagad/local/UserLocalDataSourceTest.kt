package com.project.nagad.local

import com.project.nagad.data.repository.user.UserDataSourceLocal
import com.project.nagad.local.database.UserInfoDAO
import com.project.nagad.local.source.user.UserDataSourceLocalImpl
import com.project.nagad.local.utils.TestDataGenerator
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class UserLocalDataSourceTest {

    private val userInfoLocalMapper = UserInfoLocalMapper()

    @Mock
    private lateinit var userInfoDAO: UserInfoDAO

    private lateinit var userDataSourceLocal: UserDataSourceLocal

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        userDataSourceLocal = UserDataSourceLocalImpl(
            userInfoLocalMapper,
            userInfoDAO
        )
    }

    @Test
    fun test_getUserInfo_success() {
        val userIdentifier = "1BFC9A38E6C7"
        val userInfoLocal = TestDataGenerator.generateUserInfo()

        Mockito.`when`(userInfoDAO.getUserInfo(userIdentifier))
            .thenReturn(Observable.just(userInfoLocal))

        userDataSourceLocal.getUserInfo(userIdentifier)
            .test()
            .assertSubscribed()
            .assertValue { it == userInfoLocalMapper.fromLocalToData(userInfoLocal) }
    }

    @Test
    fun test_saveUserInfo_success() {
        val userInfoLocal = TestDataGenerator.generateUserInfo()

        userDataSourceLocal.saveUserInfo(
            userInfoLocalMapper.fromLocalToData(userInfoLocal)
        )

        Mockito.verify(userInfoDAO, Mockito.times(1))
            .saveUserInfo(userInfoLocal)
    }

    @Test
    fun test_updateUserInfo_success() {
        val userInfoLocal = TestDataGenerator.generateUserInfo()

        Mockito.`when`(userInfoDAO.updateUserInfo(userInfoLocal))
            .thenReturn(Completable.complete())

        userDataSourceLocal.updateUserInfo(
            userInfoLocalMapper.fromLocalToData(userInfoLocal)
        ).test()
            .assertComplete()

        Mockito.verify(userInfoDAO, Mockito.times(1))
            .updateUserInfo(userInfoLocal)
    }

    @Test
    fun test_deleteUserInfo_success() {

//        Mockito.`when`(userInfoDAO.deleteUserInfo())
//            .thenReturn(Completable.complete())
//
//        userDataSourceLocal.deleteUserInfo()
//            .test()
//            .assertComplete()
//
//        Mockito.verify(userInfoDAO, Mockito.times(1))
//            .deleteUserInfo()
    }
}