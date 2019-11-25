package com.project.nagad.data

import com.project.nagad.data.model.UserInfoDataDomainMapper
import com.project.nagad.data.repository.user.UserDataSourceLocal
import com.project.nagad.data.repository.user.UserDataSourceRemote
import com.project.nagad.data.repository.user.UserRepositoryImpl
import com.project.nagad.data.utils.TestDataGenerator
import com.project.nagad.domain.repository.user.UserRepository
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class UserRepoImplTest {

    private lateinit var userRepository: UserRepository

    private val userInfoDataDomainMapper = UserInfoDataDomainMapper()

    @Mock
    private lateinit var userDataSourceLocal: UserDataSourceLocal
    @Mock
    private lateinit var userDataSourceRemote: UserDataSourceRemote

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        userRepository = UserRepositoryImpl(
            userInfoDataDomainMapper,
            userDataSourceLocal,
            userDataSourceRemote
        )
    }

    @Test
    fun test_getUserInfo_local_remote_interaction() {
        val userIdentifier = "1BFC9A38E6C7"
        val userInfoData = TestDataGenerator.generateUserInfo()
        val userInfoDomain = userInfoDataDomainMapper.fromDataToDomain(userInfoData)

        Mockito.`when`(userDataSourceRemote.getUserInfo(userIdentifier))
            .thenReturn(Observable.just(userInfoData))
        Mockito.`when`(userDataSourceLocal.getUserInfo(userIdentifier))
            .thenReturn(Observable.just(userInfoData))

        val testSubscriber = userRepository.getUserInfo(userIdentifier).test()

        testSubscriber.assertSubscribed()
            .assertValueCount(2)
            .assertValues(userInfoDomain, userInfoDomain)
            .assertComplete()

        Mockito.verify(userDataSourceLocal, times(1))
            .saveUserInfo(userInfoData)

        Mockito.verify(userDataSourceRemote, times(1))
            .getUserInfo(userIdentifier)
    }

    @Test
    fun test_getUserInfo_remote_error() {
        val userIdentifier = "1BFC9A38E6C7"
        val userInfoData = TestDataGenerator.generateUserInfo()
        val userInfoDomain = userInfoDataDomainMapper.fromDataToDomain(userInfoData)

        Mockito.`when`(userDataSourceRemote.getUserInfo(userIdentifier))
            .thenReturn(Observable.error(Throwable()))
        Mockito.`when`(userDataSourceLocal.getUserInfo(userIdentifier))
            .thenReturn(Observable.just(userInfoData))

        val testSubscriber = userRepository.getUserInfo(userIdentifier).test()

        testSubscriber.assertSubscribed()
            .assertValueCount(1)
            .assertValue {
                it == userInfoDomain
            }
            .assertComplete()

        Mockito.verify(userDataSourceLocal, times(1))
            .getUserInfo(userIdentifier)
    }

    @Test
    fun test_updateUserInfo_local_remote_interaction() {
        val userInfoData = TestDataGenerator.generateUserInfo()
        val userInfoEntity = userInfoDataDomainMapper.fromDataToDomain(userInfoData)

        Mockito.`when`(userDataSourceRemote.updateUserInfo(userInfoData))
            .thenReturn(Completable.complete())

        val testObserver = userRepository.updateUserInfo(userInfoEntity).test()

        testObserver.assertSubscribed()

        Mockito.verify(userDataSourceLocal, times(1))
            .saveUserInfo(userInfoData)
    }
}