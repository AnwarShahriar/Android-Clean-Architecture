package com.project.nagad.data.repository.user

import com.project.nagad.data.model.UserInfoData
import io.reactivex.Completable
import io.reactivex.Observable

interface UserDataSourceLocal {

    fun getUserInfo(identifier: String): Observable<UserInfoData>

    fun saveUserInfo(userInfoData: UserInfoData)

    fun updateUserInfo(userInfoData: UserInfoData): Completable

}