package com.wajahatkarim.movies.swvl.data

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.wajahatkarim.movies.swvl.data.local.database.MoviesDao
import com.wajahatkarim.movies.swvl.data.remote.FlickerApiService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock

@RunWith(AndroidJUnit4::class)
class SwvlRepositoryTest {

    val context = InstrumentationRegistry.getInstrumentation().targetContext

    lateinit var preferences: SharedPreferences

    @MockK
    lateinit var apiService: FlickerApiService

    @MockK
    lateinit var dao: MoviesDao

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun test_SaveMoviesInDatabaseWhenLoadedFromAssets() {
        runBlocking {
            // Given
            var moviesStr = DummyData.getAssetsMoviesStr()
            preferences = context.getSharedPreferences(context.packageName, MODE_PRIVATE)
            var repository = SwvlRepository(preferences, dao, apiService)

            // When
            coEvery { dao.insertMovies(any()) } returns Unit

            // Invoke
            repository.saveAssetMoviesInDatabase(moviesStr)

            // Then
            MatcherAssert.assertThat(repository.areMoviesInDatabase(), `is`(true))
        }
    }
}