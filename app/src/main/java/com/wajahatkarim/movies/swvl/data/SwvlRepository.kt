package com.wajahatkarim.movies.swvl.data

import android.content.SharedPreferences
import com.wajahatkarim.movies.swvl.AppConstants
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This is the single source of data for whole Swvl app.
 */
@ExperimentalCoroutinesApi
@Singleton
class SwvlRepository @Inject constructor(
    private val preferences: SharedPreferences
) {
    fun areMoviesInDatabase(): Boolean {
        return preferences.getBoolean(AppConstants.PREF_KEYS.MOVIES_IN_DATABASE, false)
    }

    fun setMoviesInDatabase(value: Boolean) {
        preferences.edit().putBoolean(AppConstants.PREF_KEYS.MOVIES_IN_DATABASE, value).commit()
    }
}