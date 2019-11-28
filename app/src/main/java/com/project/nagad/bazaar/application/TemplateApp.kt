package com.project.nagad.bazaar.application

import com.project.nagad.bazaar.BuildConfig
import com.project.nagad.bazaar.di.component.DaggerNagadBazaarAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class TemplateApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerNagadBazaarAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()

//        val crashlytics = CrashlyticsCore.Builder()
//            .disabled(BuildConfig.DEBUG)
//            .build()
//
//        Fabric.with(this, Crashlytics.Builder().core(crashlytics).build())
//
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
//        Timber.plant(CrashlyticsTree())
    }

}