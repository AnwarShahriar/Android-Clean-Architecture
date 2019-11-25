package com.project.nagad.bazaar.di.modules

import com.project.nagad.bazaar.constants.ApiConstants
import com.project.nagad.data.model.AllHomeInfoData
import com.project.nagad.data.model.UserInfoData
import com.project.nagad.data.repository.home.HomeDataSourceRemote
import com.project.nagad.data.repository.user.UserDataSourceRemote
import com.project.nagad.remote.api.HomeApiService
import com.project.nagad.remote.api.UserApiService
import com.project.nagad.remote.mapper.Mapper
import com.project.nagad.remote.model.AllHomeInfoNetwork
import com.project.nagad.remote.model.AllHomeInfoNetworkMapper
import com.project.nagad.remote.model.UserInfoNetwork
import com.project.nagad.remote.model.UserInfoNetworkMapper
import com.project.nagad.remote.source.home.HomeDataSourceRemoteImpl
import com.project.nagad.remote.source.user.UserDataSourceRemoteImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [RemoteModule.Binders::class])
class RemoteModule {

    companion object {
        const val HEADER_AUTHORIZATION = "x-api-key"
        const val HEADER_AUTHORIZATION_VALUE = "x-api-key"
        const val HEADER_CONTENT_TYPE = "Content-Type"
        const val HEADER_CONTENT_TYPE_VALUE = "application/json"
        const val CONNECT_TIMEOUT: Long = 60
        const val READ_TIMEOUT: Long = 60
        const val WRITE_TIMEOUT: Long = 60
    }

    @Module
    interface Binders {

        @Binds
        fun bindsUserRemoteSource(
            userDataSourceRemoteImpl: UserDataSourceRemoteImpl
        ): UserDataSourceRemote

        @Binds
        fun bindsHomeRemoteSource(
            homeDataSourceRemoteImpl: HomeDataSourceRemoteImpl
        ): HomeDataSourceRemote

        @Binds
        fun bindUserInfoMapper(
            userInfoNetworkMapper: UserInfoNetworkMapper
        ): Mapper<UserInfoData, UserInfoNetwork>

        @Binds
        fun bindHomeInfoMapper(
            allHomeInfoNetworkMapper: AllHomeInfoNetworkMapper
        ): Mapper<AllHomeInfoData, AllHomeInfoNetwork>
    }

    @Provides
    fun providesUserApiService(retrofit: Retrofit): UserApiService =
        retrofit.create(UserApiService::class.java)

    @Provides
    fun providesHomeApiService(retrofit: Retrofit): HomeApiService =
        retrofit.create(HomeApiService::class.java)


    @Singleton
    @Provides
    fun provideOkHttpForApi(): OkHttpClient {

        val httpClient = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor { chain ->
            val originalRequest = chain.request()

            val request = originalRequest.newBuilder()
                .addHeader(
                    HEADER_CONTENT_TYPE,
                    HEADER_CONTENT_TYPE_VALUE
                ).addHeader(
                    HEADER_AUTHORIZATION,
                    HEADER_AUTHORIZATION_VALUE
                )
                .method(originalRequest.method, originalRequest.body)
                .build()

            return@addInterceptor chain.proceed(request)
        }

        httpClient.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .addInterceptor(loggingInterceptor)
            .build()

        return httpClient.build()
    }

    @Singleton
    @Provides
    fun provideRetrofitForApi(httpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder().baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
    }

    private val loggingInterceptor = Interceptor { chain ->
        val request = chain.request()
        val t1 = System.nanoTime()
        Timber.i("Sending request %s on %s%n%s", request.url, chain.connection(), request.headers)
        val response = chain.proceed(request)
        val t2 = System.nanoTime()
        Timber.i(
            "Received response for %s in %.1fms%n%s",
            response.request.url, (t2 - t1) / 1e6, response.headers
        )

        return@Interceptor response

    }

}