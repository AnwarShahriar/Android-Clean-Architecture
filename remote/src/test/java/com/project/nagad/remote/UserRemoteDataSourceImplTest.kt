package com.project.nagad.remote

import com.project.nagad.data.repository.user.UserDataSourceRemote
import com.project.nagad.remote.api.UserApiService
import com.project.nagad.remote.model.ResponseWrapper
import com.project.nagad.remote.model.UserInfoNetworkMapper
import com.project.nagad.remote.source.user.UserDataSourceRemoteImpl
import com.project.nagad.remote.utils.TestDataGenerator
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
class UserRemoteDataSourceImplTest {

    @Mock
    private lateinit var userApiService: UserApiService

    private val userInfoNetworkMapper = UserInfoNetworkMapper()

    private lateinit var userDataSourceRemote: UserDataSourceRemote

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        userDataSourceRemote = UserDataSourceRemoteImpl(
            userInfoNetworkMapper,
            userApiService
        )
    }

    @Test
    fun test_getUserInfo_success() {
//        val userInfoNetwork = TestDataGenerator.generateUserInfoNetwork()
//        val userIdentifier = "AEZ19EDH2QZ"
//
//        val mockResponse = ResponseWrapper(
//            userInfoNetwork
//        )
//
//        Mockito.`when`(userApiService.getUserInformation())
//            .thenReturn(Observable.just(mockResponse))
//
//        userDataSourceRemote.getUserInfo(userIdentifier)
//            .test()
//            .assertSubscribed()
//            .assertValue {
//                val data = userInfoNetworkMapper.toNetworkFromData(it)
//                data == userInfoNetwork
//            }
//            .assertComplete()
//
//        Mockito.verify(userApiService, Mockito.times(1))
//            .getUserInformation()
    }

    @Test
    fun test_getUserInfo_error() {
        val userIdentifier = "AEZ19EDH2QZ"
        val errorMsg = "ERROR"

        Mockito.`when`(userApiService.getUserInformation())
            .thenReturn(Observable.error(Throwable(errorMsg)))

        userDataSourceRemote.getUserInfo(userIdentifier)
            .test()
            .assertSubscribed()
            .assertError {
                it.message == errorMsg
            }
            .assertNotComplete()
    }

    @Test
    fun test_updateUserInfo_success() {
        val userInfoData = TestDataGenerator.generateUserInfoData()

        Mockito.`when`(userApiService.updateUserInformation(userInfoData))
            .thenReturn(Completable.complete())

        userDataSourceRemote.updateUserInfo(userInfoData)
            .test()
            .assertSubscribed()
            .assertComplete()

        Mockito.verify(userApiService, Mockito.times(1))
            .updateUserInformation(userInfoData)
    }

    @Test
    fun test_updateUserInfo_error() {
        val userInfoData = TestDataGenerator.generateUserInfoData()
        val errorMsg = "ERROR"

        Mockito.`when`(userApiService.updateUserInformation(userInfoData))
            .thenReturn(Completable.error(Throwable(errorMsg)))

        userDataSourceRemote.updateUserInfo(userInfoData)
            .test()
            .assertSubscribed()
            .assertError {
                it.message == errorMsg
            }
            .assertNotComplete()
    }
}