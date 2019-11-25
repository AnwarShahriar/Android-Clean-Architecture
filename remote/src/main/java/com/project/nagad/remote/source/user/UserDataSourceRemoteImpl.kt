package com.project.nagad.remote.source.user

import com.project.nagad.data.model.UserInfoData
import com.project.nagad.data.repository.user.UserDataSourceRemote
import com.project.nagad.remote.api.UserApiService
import com.project.nagad.remote.mapper.Mapper
import com.project.nagad.remote.model.UserInfoNetwork
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class UserDataSourceRemoteImpl @Inject constructor(

    /** @param UserInfoData is upper level model class. Here Remote is lower level of Data module */
    private val userInfoNetworkMapper: Mapper<UserInfoData, UserInfoNetwork>,
    private val userApiService: UserApiService
) : UserDataSourceRemote {

    override fun getUserInfo(identifier: String): Observable<UserInfoData> {
        return userApiService.getUserInformation()
            .map { response ->
                println("Remote invoked")
                userInfoNetworkMapper.fromNetworkToData(response.userInfoNetwork)
            }
    }

    override fun updateUserInfo(userInfoData: UserInfoData): Completable {
        return userApiService.updateUserInformation(userInfoData)
            .doOnError { e -> e.localizedMessage }
            .doOnComplete{
                println("OnComplete invoked")
            }
    }
}