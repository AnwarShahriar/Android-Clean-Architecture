package com.project.nagad.bazaar.di.modules

import com.project.nagad.data.mapper.Mapper
import com.project.nagad.data.model.AllHomeInfoData
import com.project.nagad.data.model.AllHomeInfoDataDomainMapper
import com.project.nagad.data.model.UserInfoData
import com.project.nagad.data.model.UserInfoDataDomainMapper
import com.project.nagad.data.repository.home.HomeRepositoryImpl
import com.project.nagad.data.repository.user.UserRepositoryImpl
import com.project.nagad.domain.entities.AllHomeInfoEntity
import com.project.nagad.domain.entities.UserInfoEntity
import com.project.nagad.domain.repository.home.HomeRepository
import com.project.nagad.domain.repository.user.UserRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindsUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    abstract fun bindsHomeInfoRepository(
        allHomeInfoRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository

    @Binds
    abstract fun bindsUserInfoDataMapper(
        userInfoDataDomainMapper: UserInfoDataDomainMapper
    ): Mapper<UserInfoEntity, UserInfoData>

    @Binds
    abstract fun bindsAllHomeInfoDataMapper(
        allHomeInfoDataDomainMapper: AllHomeInfoDataDomainMapper
    ): Mapper<AllHomeInfoEntity, AllHomeInfoData>
}