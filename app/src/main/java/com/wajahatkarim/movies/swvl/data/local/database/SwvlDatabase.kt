package com.wajahatkarim.movies.swvl.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wajahatkarim.movies.swvl.model.MovieModel

/**
 * The Swvl App's local SQLite database powered by Room wrapper.
 */
@Database(
    entities = [MovieModel::class],
    version = SwvlDatabase.VERSION
)
@TypeConverters(JsonListConverter::class)
abstract class SwvlDatabase: RoomDatabase() {
    /**
     * Allows the app to access database through this DAO object
     * @return [MoviesDao] Data Access Object.
     */
    abstract fun getMoviesDao(): MoviesDao

    companion object {
        const val VERSION = 1
        const val DB_NAME = "swvl_database"

        @Volatile
        private var _instance: SwvlDatabase? = null

        fun getInstance(context: Context): SwvlDatabase {
            val tempInstance = _instance
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SwvlDatabase::class.java,
                    DB_NAME
                ).build()

                _instance = instance
                return instance
            }
        }
    }
}