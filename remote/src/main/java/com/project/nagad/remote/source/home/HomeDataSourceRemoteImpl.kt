package com.project.nagad.remote.source.home

import com.project.nagad.data.model.AllHomeInfoData
import com.project.nagad.data.repository.home.HomeDataSourceRemote
import com.project.nagad.remote.api.HomeApiService
import com.project.nagad.remote.mapper.Mapper
import com.project.nagad.remote.model.AllHomeInfoNetwork
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

class HomeDataSourceRemoteImpl @Inject constructor(
    private val allHomeInfoNetworkMapper: Mapper<AllHomeInfoData, AllHomeInfoNetwork>,
    private val homeApiService: HomeApiService
) : HomeDataSourceRemote {

    override fun getAllHomeInfo(): Observable<AllHomeInfoData> {
        return homeApiService.getHomePageProducts()
            .map { response ->
                Timber.d("Remote Invoked")
                allHomeInfoNetworkMapper.fromNetworkToData(response.allHomeInfoNetwork)
            }
    }
}