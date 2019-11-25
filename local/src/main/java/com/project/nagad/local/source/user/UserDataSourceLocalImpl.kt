package com.project.nagad.local.source.user

import com.project.nagad.data.model.UserInfoData
import com.project.nagad.data.repository.user.UserDataSourceLocal
import com.project.nagad.local.database.UserInfoDAO
import com.project.nagad.local.mapper.Mapper
import com.project.nagad.local.model.UserInfoLocal
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class UserDataSourceLocalImpl @Inject constructor(
    private val userInfoMapper: Mapper<UserInfoData, UserInfoLocal>,
    private val userInfoDAO: UserInfoDAO
) : UserDataSourceLocal {

    override fun getUserInfo(identifier: String): Observable<UserInfoData> {
        return userInfoDAO.getUserInfo(identifier)
            .map { userInfoMapper.fromLocalToData(it) }
    }

    override fun saveUserInfo(userInfoData: UserInfoData) {
        return userInfoDAO.saveUserInfo(
            userInfoMapper.toLocalFromData(userInfoData)
        )
    }

    override fun updateUserInfo(userInfoData: UserInfoData): Completable {
        return userInfoDAO.updateUserInfo(
            userInfoMapper.toLocalFromData(userInfoData)
        )
    }
}