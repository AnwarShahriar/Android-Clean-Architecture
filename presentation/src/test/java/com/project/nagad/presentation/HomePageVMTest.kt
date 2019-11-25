package com.project.nagad.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.project.nagad.domain.repository.home.HomeRepository
import com.project.nagad.domain.repository.user.UserRepository
import com.project.nagad.domain.usercases.home.GetAllHomeInfoUseCase
import com.project.nagad.domain.usercases.user.GetUserInfoUseCase
import com.project.nagad.presentation.model.AllHomeInfoDomainMapper
import com.project.nagad.presentation.model.Status
import com.project.nagad.presentation.model.UserInfoDomainMapper
import com.project.nagad.presentation.utils.TestDataGenerator
import com.project.nagad.presentation.viewmodels.HomePageVM
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class HomePageVMTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var userRepository: UserRepository

    @Mock
    lateinit var homeRepository: HomeRepository

    private lateinit var homePageVM: HomePageVM
    private val userInfoDomainMapper = UserInfoDomainMapper()
    private val homeInfoDomainMapper = AllHomeInfoDomainMapper()

    private val userInfo = TestDataGenerator.generateUserInfo()
    private val userInfoEntity = userInfoDomainMapper.fromViewToDomain(userInfo)

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        val getUserInfoTask = GetUserInfoUseCase(
            userRepository,
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )

        val getHomeInfoTask = GetAllHomeInfoUseCase(
            homeRepository,
            Schedulers.trampoline(),
            Schedulers.trampoline()
        )

        homePageVM = HomePageVM(
            userInfoDomainMapper,
            homeInfoDomainMapper,
            getUserInfoTask,
            getHomeInfoTask
        )
    }

    @Test
    fun test_getUserInfo_success() {

        Mockito.`when`(userRepository.getUserInfo(anyString()))
            .thenReturn(Observable.just(userInfoEntity))

        val userInfoResource = homePageVM.userInfoResource

        userInfoResource.observeForever { /*Do nothing*/ }

        Assert.assertTrue(
            userInfoResource.value?.status == Status.SUCCESS
                    && userInfoResource.value?.data == userInfo
        )
    }

    @Test
    fun test_getUserInfo_error() {
        val errorMsg = "user info error in fetching data"
        Mockito.`when`(userRepository.getUserInfo(anyString()))
            .thenReturn(Observable.error(Throwable(errorMsg)))

        val userInfoResource = homePageVM.userInfoResource

        userInfoResource.observeForever { /*Do nothing*/ }

        Assert.assertTrue(
            userInfoResource.value?.status == Status.ERROR
                    && userInfoResource.value?.message == errorMsg
        )
    }
}