package com.project.nagad.data.repository.user

import com.project.nagad.data.mapper.Mapper
import com.project.nagad.data.model.UserInfoData
import com.project.nagad.domain.entities.UserInfoEntity
import com.project.nagad.domain.repository.user.UserRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userInfoMapper: Mapper<UserInfoEntity, UserInfoData>,
    private val userDataSourceLocal: UserDataSourceLocal,
    private val userDataSourceRemote: UserDataSourceRemote
) : UserRepository {

    override fun getUserInfo(identifier: String): Observable<UserInfoEntity> {

        val userInfoObservable = userDataSourceLocal.getUserInfo(identifier)
            .map {
                println("userInfoObservable")
                userInfoMapper.fromDataToDomain(it) }

        return userDataSourceRemote.getUserInfo(identifier)
            .map {
                println("userDataSourceRemote")
                userDataSourceLocal.saveUserInfo(it)
                userInfoMapper.fromDataToDomain(it)
            }.onErrorResumeNext(Observable.empty())
            .concatWith(userInfoObservable)

    }

    override fun updateUserInfo(userInfoEntity: UserInfoEntity): Completable {

        return userDataSourceRemote.updateUserInfo(userInfoMapper.toDataFromDomain(userInfoEntity))
            .doOnComplete {
                userDataSourceLocal.saveUserInfo(
                    userInfoMapper.toDataFromDomain(
                        userInfoEntity
                    )
                )
            }

    }
}