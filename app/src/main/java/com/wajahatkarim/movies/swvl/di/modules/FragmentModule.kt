package com.wajahatkarim.movies.swvl.di.modules

import com.wajahatkarim.movies.swvl.base.BaseFragment
import com.wajahatkarim.movies.swvl.ui.moviedetails.MovieDetailsFragment
import com.wajahatkarim.movies.swvl.ui.movieslist.MoviesListFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun bindMoviesListFragment(): MoviesListFragment

    @ContributesAndroidInjector
    abstract fun bindMovieDetailsFragment(): MovieDetailsFragment

}