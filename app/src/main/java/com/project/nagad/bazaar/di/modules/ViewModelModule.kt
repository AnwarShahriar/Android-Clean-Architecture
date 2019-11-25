package com.project.nagad.bazaar.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.nagad.bazaar.di.component.ViewModelKey
import com.project.nagad.presentation.factory.ViewModelFactory
import com.project.nagad.presentation.viewmodels.HomePageVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindsViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomePageVM::class)
    abstract fun bindsHomeViewModel(homeViewModel: HomePageVM): ViewModel
}