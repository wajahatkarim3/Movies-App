package com.wajahatkarim.movies.swvl.di.modules

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferencesModule {

    @Singleton
    @Provides
    fun providesSharedPreferences(app: Application): SharedPreferences = app.getSharedPreferences(app.packageName, MODE_PRIVATE)
}