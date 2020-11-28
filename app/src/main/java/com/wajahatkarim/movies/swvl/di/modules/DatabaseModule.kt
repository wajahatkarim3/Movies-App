package com.wajahatkarim.movies.swvl.di.modules

import android.app.Application
import com.wajahatkarim.movies.swvl.data.local.database.SwvlDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun getDatabase(application: Application) = SwvlDatabase.getInstance(application)

    @Singleton
    @Provides
    fun getMoviesDao(database: SwvlDatabase) = database.getMoviesDao()
}