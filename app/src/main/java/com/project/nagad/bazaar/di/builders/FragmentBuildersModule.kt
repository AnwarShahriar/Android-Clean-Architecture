package com.project.nagad.bazaar.di.builders

import com.project.nagad.bazaar.features.homeProduct.ui.HomeProductFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributesHomeFragment(): HomeProductFragment
}