package com.wajahatkarim.movies.swvl.di.modules

import com.wajahatkarim.movies.swvl.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}