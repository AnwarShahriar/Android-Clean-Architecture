package com.project.nagad.remote.api

import com.project.nagad.remote.model.ResponseWrapper
import io.reactivex.Observable
import retrofit2.http.GET

interface HomeApiService {

    @GET("all-home-products/")
    fun getHomePageProducts(): Observable<ResponseWrapper>
}