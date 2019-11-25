package com.project.nagad.bazaar.di.modules

import com.project.nagad.domain.entities.AllHomeInfoEntity
import com.project.nagad.domain.entities.UserInfoEntity
import com.project.nagad.presentation.mapper.Mapper
import com.project.nagad.presentation.model.AllHomeInfo
import com.project.nagad.presentation.model.AllHomeInfoDomainMapper
import com.project.nagad.presentation.model.UserInfo
import com.project.nagad.presentation.model.UserInfoDomainMapper
import dagger.Binds
import dagger.Module

@Module
abstract class PresentationModule {

    @Binds
    abstract fun bindsUserInfoMapper(
        userInfoDomainMapper: UserInfoDomainMapper
    ): Mapper<UserInfoEntity, UserInfo>

    @Binds
    abstract fun bindsAllHomeInfoMapper(
        allHomeInfoDomainMapper: AllHomeInfoDomainMapper
    ): Mapper<AllHomeInfoEntity, AllHomeInfo>
}