package com.project.nagad.domain.usecases

import com.project.nagad.domain.repository.user.UserRepository
import com.project.nagad.domain.usercases.user.UpdateUserInfoUseCase
import com.project.nagad.domain.utils.TestDataGenerator
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class UpdateUserInfoTest {

    private lateinit var updateUserInfoUseCase: UpdateUserInfoUseCase

    @Mock
    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        updateUserInfoUseCase = UpdateUserInfoUseCase(
            userRepository,
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )
    }

    @Test
    fun test_updateUserInfo_success() {
        val userInfoEntity = TestDataGenerator.generateUserInfo()

        Mockito.`when`(userRepository.updateUserInfo(userInfoEntity))
            .thenReturn(Completable.complete())

        val testObserver = updateUserInfoUseCase
            .buildUseCase(UpdateUserInfoUseCase.Params(userInfoEntity))
            .test()

        Mockito.verify(userRepository, Mockito.times(1))
            .updateUserInfo(userInfoEntity)

        testObserver
            .assertSubscribed()
            .assertComplete()
    }

    @Test
    fun test_getUserInfo_error() {
        val userInfoEntity = TestDataGenerator.generateUserInfo()
        val errorMsg = "Error Occured"

        Mockito.`when`(userRepository.updateUserInfo(userInfoEntity))
            .thenReturn(Completable.error(Throwable(errorMsg)))

        val testObserver = updateUserInfoUseCase
            .buildUseCase(UpdateUserInfoUseCase.Params(userInfoEntity))
            .test()

        Mockito.verify(userRepository, Mockito.times(1))
            .updateUserInfo(userInfoEntity)

        testObserver
            .assertSubscribed()
            .assertError { it.message?.equals(errorMsg) ?: false }
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_updateUserInfoTaskNoParameters_error() {
//        val userInfo = TestDataGenerator.generateUpgradableUserInfo()

        val testObserver = updateUserInfoUseCase.buildUseCase().test()
        testObserver.assertSubscribed()
    }
}