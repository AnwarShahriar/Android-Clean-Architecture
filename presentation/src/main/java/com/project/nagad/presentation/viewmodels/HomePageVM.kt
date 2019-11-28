package com.project.nagad.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.toLiveData
import com.project.nagad.domain.entities.AllHomeInfoEntity
import com.project.nagad.domain.entities.UserInfoEntity
import com.project.nagad.domain.usercases.home.GetAllHomeInfoUseCase
import com.project.nagad.domain.usercases.user.GetUserInfoUseCase
import com.project.nagad.presentation.mapper.Mapper
import com.project.nagad.presentation.model.AllHomeInfo
import com.project.nagad.presentation.model.Resource
import com.project.nagad.presentation.model.UserInfo
import com.project.nagad.presentation.viewmodels.base.BaseViewModel
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject

class HomePageVM @Inject internal constructor(
    private val userInfoDomainMapper: Mapper<UserInfoEntity, UserInfo>,
    private val allHomeInfoDomainMapper: Mapper<AllHomeInfoEntity, AllHomeInfo>,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getAllHomeInfoUseCase: GetAllHomeInfoUseCase
) : BaseViewModel() {

    private val userInfoTrigger = MutableLiveData<String>()

    val userInfoResource: LiveData<Resource<UserInfo>> =
        Transformations.switchMap(userInfoTrigger) {
            getUserInfoUseCase
                .buildUseCase(GetUserInfoUseCase.Params(userInfoTrigger.value!!))
                .doOnSubscribe {
                    println("calling do on subscribe")
                    addDisposables(it)
                }
                .map {
                    println("calling map 1")
                    userInfoDomainMapper.toViewFromDomain(it)
                }
                .map {
                    println("calling map 2")
                    Resource.success(it)
                }
                .startWith(Resource.loading())
                .onErrorResumeNext(
                    Function {
                        println("calling on error resume next fun")
                        Observable.just(Resource.error(it.localizedMessage))
                    }
                )
                .toFlowable(BackpressureStrategy.LATEST)
                .toLiveData()
        }

    val allHomeInfoResource: LiveData<Resource<AllHomeInfo>>
        get() = getAllHomeInfoUseCase
            .buildUseCase(GetAllHomeInfoUseCase.Params("REH99PAG4EW5"))
            .map { allHomeInfoDomainMapper.toViewFromDomain(it) }
            .map { Resource.success(it) }
            .startWith(Resource.loading())
            .doOnError { e -> e.localizedMessage }
            .doOnSubscribe { addDisposables(it) }
            .toFlowable(BackpressureStrategy.LATEST)
            .toLiveData()

    fun fetchUserInfo(identifier: String) {
        userInfoTrigger.postValue(identifier)
    }
}