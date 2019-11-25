package com.project.nagad.domain.usecases

import com.project.nagad.domain.repository.user.UserRepository
import com.project.nagad.domain.usercases.user.GetUserInfoUseCase
import com.project.nagad.domain.utils.TestDataGenerator
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class GetUserInfoTest {

    private lateinit var getUserInfoUseCase: GetUserInfoUseCase

    @Mock
    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getUserInfoUseCase = GetUserInfoUseCase(
            userRepository,
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )
    }

    @Test
    fun test_getUserInfo_success() {
        val userInfo = TestDataGenerator.generateUserInfo()

        Mockito.`when`(userRepository.getUserInfo(userInfo.userIdentifier))
            .thenReturn(Observable.just(userInfo))

        val testObserver = getUserInfoUseCase
            .buildUseCase(GetUserInfoUseCase.Params(userInfo.userIdentifier))
            .test()

        Mockito.verify(userRepository, times(1))
            .getUserInfo(userInfo.userIdentifier)

        testObserver.assertSubscribed()
            .assertValue { it == userInfo }
            .assertComplete()
    }

    @Test
    fun test_getUserInfo_error() {
        val userInfo = TestDataGenerator.generateUserInfo()
        val errorMsg = "Error Occurred"

        Mockito.`when`(userRepository.getUserInfo(userInfo.userIdentifier))
            .thenReturn(Observable.error(Throwable(errorMsg)))

        val testObserver = getUserInfoUseCase
            .buildUseCase(GetUserInfoUseCase.Params(userInfo.userIdentifier))
            .test()

        Mockito.verify(userRepository, times(1))
            .getUserInfo(userInfo.userIdentifier)

        testObserver.assertSubscribed()
            .assertError { it.message?.equals(errorMsg) ?: false }
            .assertNotComplete()
    }

    @Test
    fun test_AccountUpgradeEligibility() {
        val upgradableUserInfo = TestDataGenerator.generateUpgradableUserInfo()
        assert(upgradableUserInfo.isEligibleForUpgrade)

        val userInfo = TestDataGenerator.generateUserInfo()
        assert(!userInfo.isEligibleForUpgrade)
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_getUserInfoTaskNoParameters_error() {
//        val userInfo = TestDataGenerator.generateUpgradableUserInfo()

        val testObserver = getUserInfoUseCase.buildUseCase().test()
        testObserver.assertSubscribed()
    }
}
