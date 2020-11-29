package com.wajahatkarim.movies.swvl.di.components

import android.app.Application
import com.wajahatkarim.movies.swvl.SwvlApp
import com.wajahatkarim.movies.swvl.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelsFactoryModule::class,
        ViewModelModule::class,
        PreferencesModule::class,
        DatabaseModule::class,
        FlickerApiModule::class
    ]
)
interface AppComponent : AndroidInjector<SwvlApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(app: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: SwvlApp)
}