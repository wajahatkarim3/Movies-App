package com.wajahatkarim.movies.swvl.ui.moviedetails

import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.any
import com.wajahatkarim.movies.swvl.AppConstants
import com.wajahatkarim.movies.swvl.BuildConfig
import com.wajahatkarim.movies.swvl.data.DataState
import com.wajahatkarim.movies.swvl.data.SwvlRepository
import com.wajahatkarim.movies.swvl.data.local.database.MoviesDao
import com.wajahatkarim.movies.swvl.data.remote.FlickerApiService
import com.wajahatkarim.movies.swvl.model.ImageModel
import com.wajahatkarim.movies.swvl.util.DummyData
import com.wajahatkarim.movies.swvl.util.TestCoroutineRule
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@RunWith(JUnit4::class)
class MovieDetailsViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var repository: SwvlRepository

    lateinit var viewModel: MovieDetailsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(TestCoroutineDispatcher())
        viewModel = MovieDetailsViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test when movie details viewmodel is initialized, photos are fetched successfully`() {
        runBlocking {
            // Given
            val uiObserver = mockk<Observer<MovieDetailsUiState>>(relaxed = true)
            val photosObserver = mockk<Observer<List<ImageModel>>>(relaxed = true)

            // When
            coEvery { repository.searchMovieImages(query = any()) } returns flowOf(DataState.success(DummyData.getPhotosList()))
            viewModel = MovieDetailsViewModel(repository)
            viewModel.uiState.observeForever(uiObserver)
            viewModel.photosList.observeForever(photosObserver)

            // Invoke
            viewModel.init(DummyData.getSingleMovie())

    Dispatchers.resetMain()        // Then
            verify { uiObserver.onChanged(match { it == ContentState }) }
            verify { photosObserver.onChanged(match { it.size == DummyData.getPhotosList().size }) }
        }
    }
}