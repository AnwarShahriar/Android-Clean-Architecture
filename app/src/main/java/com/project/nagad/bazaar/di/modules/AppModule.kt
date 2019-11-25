package com.project.nagad.bazaar.di.modules

import com.project.nagad.bazaar.di.builders.ActivityBuildersModule
import com.project.nagad.bazaar.di.builders.FragmentBuildersModule
import dagger.Module

@Module(
    includes = [ActivityBuildersModule::class,
        FragmentBuildersModule::class,
        PresentationModule::class,
        ViewModelModule::class,
        DomainModule::class,
        DataModule::class,
        LocalPersistenceModule::class,
        RemoteModule::class]
)
class AppModule