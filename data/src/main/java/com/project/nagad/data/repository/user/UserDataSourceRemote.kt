package com.project.nagad.data.repository.user

import com.project.nagad.data.model.UserInfoData
import io.reactivex.Completable
import io.reactivex.Observable

interface UserDataSourceRemote {

    fun getUserInfo(identifier: String): Observable<UserInfoData>

    fun updateUserInfo(userInfoData: UserInfoData): Completable
}