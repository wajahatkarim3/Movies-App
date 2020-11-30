package com.wajahatkarim.movies.swvl.data.local.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wajahatkarim.movies.swvl.data.DummyData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MoviesDaoTest {

    private lateinit var swvlDatabase: SwvlDatabase

    @Before
    fun setUp() {
        swvlDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            SwvlDatabase::class.java
        ).build()
    }

    @After
    fun tearDown() {
        swvlDatabase.close()
    }

    @Test
    @Throws(InterruptedException::class)
    fun test_insertMoviesInDatabase() {
        runBlocking {
            // Given
            var movies = DummyData.getMoviesList()

            // Invoke
            swvlDatabase.getMoviesDao().insertMovies(movies)

            // Then
            MatcherAssert.assertThat(swvlDatabase.getMoviesDao().getAllMovies().first(), CoreMatchers.equalTo(DummyData.getMoviesList()))
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun test_deleteAllMoviesInDatabase() {
        runBlocking {
            // Invoke
            swvlDatabase.getMoviesDao().deleteAllMovies()

            // Then
            MatcherAssert.assertThat(swvlDatabase.getMoviesDao().getAllMovies().first().size, CoreMatchers.equalTo(0))
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun test_searchMoviesByNameInDatabase() {
        runBlocking {
            // Given
            var movies = DummyData.getMoviesList()
            swvlDatabase.getMoviesDao().insertMovies(movies)

            // Invoke
            var dbMovies = swvlDatabase.getMoviesDao().getMoviesByName("17 Again")

            // Then
            MatcherAssert.assertThat(dbMovies[0], CoreMatchers.equalTo(movies[2]))
        }
    }
}