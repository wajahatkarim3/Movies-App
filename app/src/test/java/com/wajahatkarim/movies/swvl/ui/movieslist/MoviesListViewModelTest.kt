package com.wajahatkarim.movies.swvl.ui.movieslist

import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import com.wajahatkarim.movies.swvl.data.SwvlRepository
import com.wajahatkarim.movies.swvl.data.local.database.MoviesDao
import com.wajahatkarim.movies.swvl.util.DummyData
import com.wajahatkarim.movies.swvl.util.TestCoroutineRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.*
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class MoviesListViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var preferences: SharedPreferences

    @Mock
    lateinit var dao: MoviesDao

    @Mock
    lateinit var uiStateObserver: Observer<MoviesListUiState>

    @InjectMocks
    lateinit var repository: SwvlRepository

    lateinit var viewModel: MoviesListViewModel
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MoviesListViewModel(repository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `test when movies are not in database, then load movies from Assets`() {
        // Given
        var moviesInDatabase = false
        viewModel.uiStateLiveData.observeForever(uiStateObserver)

        runBlockingTest {
            // When
            whenever(repository.areMoviesInDatabase()) doReturn(moviesInDatabase)

            viewModel.loadMovies()

            // Then
            try {
                verify(uiStateObserver).onChanged(ReadAssetsState)
            } finally {
                viewModel.uiStateLiveData.removeObserver(uiStateObserver)
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test when movies are in database, then load from database`() {
        // Given
        var moviesInDatabase = true
        var moviesList = DummyData.getMoviesList()
        viewModel.uiStateLiveData.observeForever(uiStateObserver)
        Dispatchers.setMain(testDispatcher)

        // When
        whenever(repository.areMoviesInDatabase()) doReturn(moviesInDatabase)
        testCoroutineRule.runBlockingTest {
            whenever(repository.getAllMoviesFromDatabase()) doReturn(flowOf(moviesList))

            // Action
            viewModel.loadMovies()

            // Then
            try {
                verify(uiStateObserver).onChanged(LoadingState)
                verify(uiStateObserver).onChanged(ContentState)
            } finally {
                viewModel.uiStateLiveData.removeObserver(uiStateObserver)
            }
        }
    }
}