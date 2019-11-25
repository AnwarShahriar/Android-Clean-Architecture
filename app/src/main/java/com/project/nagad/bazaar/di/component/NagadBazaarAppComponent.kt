package com.project.nagad.bazaar.di.component

import android.app.Application
import com.project.nagad.bazaar.application.TemplateApp
import com.project.nagad.bazaar.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class
    ]
)
interface NagadBazaarAppComponent : AndroidInjector<TemplateApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): NagadBazaarAppComponent
    }

    override fun inject(app: TemplateApp)
}
