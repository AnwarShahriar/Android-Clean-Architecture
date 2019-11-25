package com.project.nagad.bazaar.di.builders

import com.project.nagad.bazaar.features.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity
}