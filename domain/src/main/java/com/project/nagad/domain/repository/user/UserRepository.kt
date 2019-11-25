package com.project.nagad.domain.repository.user

import com.project.nagad.domain.entities.UserInfoEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface UserRepository {

    fun getUserInfo(identifier: String): Observable<UserInfoEntity>

    fun updateUserInfo(userInfoEntity: UserInfoEntity): Completable
}