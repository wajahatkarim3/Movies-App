package com.wajahatkarim.movies.swvl.data

import android.content.SharedPreferences
import com.google.gson.Gson
import com.wajahatkarim.movies.swvl.AppConstants
import com.wajahatkarim.movies.swvl.data.local.assets.AssetMoviesResponse
import com.wajahatkarim.movies.swvl.data.local.database.MoviesDao
import com.wajahatkarim.movies.swvl.model.MovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This is the single source of data for whole Swvl app.
 */
@ExperimentalCoroutinesApi
@Singleton
class SwvlRepository @Inject constructor(
    private val preferences: SharedPreferences,
    private val moviesDao: MoviesDao
) {
    suspend fun saveAssetMoviesInDatabase(moviesStr: String) {
        var gson = Gson()
        var assetsMoviesResponse = gson.fromJson(moviesStr, AssetMoviesResponse::class.java)
        assetsMoviesResponse.movies?.let { movies ->
            moviesDao.insertMovies(movies)

            // Save in Preferences that movies are stored
            setMoviesInDatabase(true)
        }
    }

    fun getAllMoviesFromDatabase(): Flow<List<MovieModel>> = moviesDao.getAllMovies().flowOn(Dispatchers.IO)

    fun areMoviesInDatabase(): Boolean {
        return preferences.getBoolean(AppConstants.PREF_KEYS.MOVIES_IN_DATABASE, false)
    }

    fun setMoviesInDatabase(value: Boolean) {
        preferences.edit().putBoolean(AppConstants.PREF_KEYS.MOVIES_IN_DATABASE, value).commit()
    }
}