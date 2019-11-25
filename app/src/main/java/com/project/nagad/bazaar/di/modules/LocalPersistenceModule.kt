package com.project.nagad.bazaar.di.modules

import android.app.Application
import com.project.nagad.data.model.UserInfoData
import com.project.nagad.data.repository.user.UserDataSourceLocal
import com.project.nagad.local.database.NagadBazaarDB
import com.project.nagad.local.database.pref.AppPrefs
import com.project.nagad.local.mapper.Mapper
import com.project.nagad.local.model.UserInfoLocal
import com.project.nagad.local.model.UserInfoLocalMapper
import com.project.nagad.local.source.user.UserDataSourceLocalImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [LocalPersistenceModule.Binders::class])
class LocalPersistenceModule {

    @Module
    interface Binders {

        @Binds
        fun bindsUserDataSourceLocal(
            userDataSourceLocalImpl: UserDataSourceLocalImpl
        ): UserDataSourceLocal

        @Binds
        fun bindUserInfoLocalMapper(
            userInfoLocalMapper: UserInfoLocalMapper
        ): Mapper<UserInfoData, UserInfoLocal>
    }

    @Provides
    @Singleton
    fun provideAppPrefs(application: Application): AppPrefs {
        return AppPrefs(application.applicationContext)
    }

    @Provides
    @Singleton
    fun providesDatabase(
        application: Application
    ) = NagadBazaarDB.getInstance(application.applicationContext)

    @Provides
    @Singleton
    fun providesUserInfoDAO(
        nagadBazaarDB: NagadBazaarDB
    ) = nagadBazaarDB.getUserInfoDao()
}