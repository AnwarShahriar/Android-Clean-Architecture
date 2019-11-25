package com.project.nagad.remote.api

import com.project.nagad.data.model.UserInfoData
import com.project.nagad.remote.model.ResponseWrapper
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApiService {

    @GET("user-information/")
    fun getUserInformation():
            Observable<ResponseWrapper>

    @POST("api/user/update")
    fun updateUserInformation(
        @Body userInfoData: UserInfoData
    ): Completable
}