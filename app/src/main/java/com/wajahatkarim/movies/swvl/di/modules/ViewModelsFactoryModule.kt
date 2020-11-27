package com.wajahatkarim.movies.swvl.di.modules

import androidx.lifecycle.ViewModelProvider
import com.wajahatkarim.movies.swvl.di.factories.SwvlViewModelProviderFactory
import dagger.Binds
import dagger.Module

/**
 * This modules is responsible for creating ViewModels for screens with the help of SwvlViewModelProvider Factory.
 */
@Module
interface ViewModelsFactoryModule {

    @Binds
    fun bindSwvlViewModelFactory(factory: SwvlViewModelProviderFactory) : ViewModelProvider.Factory
}