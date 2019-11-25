package com.project.nagad.data.repository.home

import com.project.nagad.data.mapper.Mapper
import com.project.nagad.data.model.AllHomeInfoData
import com.project.nagad.domain.entities.AllHomeInfoEntity
import com.project.nagad.domain.repository.home.HomeRepository
import io.reactivex.Observable
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val allHomeInfoDataDomainMapper: Mapper<AllHomeInfoEntity, AllHomeInfoData>,
    private val homeDataSourceRemote: HomeDataSourceRemote
) : HomeRepository {

    override fun getAllHomePageInfo(): Observable<AllHomeInfoEntity> {
        return homeDataSourceRemote.getAllHomeInfo()
            .map {
                allHomeInfoDataDomainMapper.fromDataToDomain(it)
            }.onErrorResumeNext(Observable.empty())
    }
}