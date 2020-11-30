package com.wajahatkarim.movies.swvl.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.notNull
import com.wajahatkarim.movies.swvl.BuildConfig
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class FlickerApiServiceTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var apiService: FlickerApiService
    var mockWebServer = MockWebServer()

    @Before
    fun setUp() {
        mockWebServer.start()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FlickerApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `test flicker search images api`() {
        runBlocking {
            // Given
            var responseStream = javaClass.classLoader!!.getResourceAsStream("dummy_flicker_images.json")
            var buffer = responseStream.source().buffer()
            val mockResponse = MockResponse()

            // When
            mockWebServer.enqueue(mockResponse.setBody(buffer.readString(Charsets.UTF_8)))

            // Invoke
            val photosResponse = apiService.getImages(format = "json", callback =  1, apiKey = BuildConfig.FLICKER_API_KEY, title = "17 Again", perPage = 3, page = 1).body()

            // Then
            MatcherAssert.assertThat(photosResponse, notNullValue())
            MatcherAssert.assertThat(photosResponse!!.photos, notNullValue())
            MatcherAssert.assertThat(photosResponse!!.photos!!.photos, notNullValue())
            MatcherAssert.assertThat(photosResponse!!.photos!!.photos!!.size, `is`(3))
            MatcherAssert.assertThat(photosResponse!!.photos!!.photos!![0].title, `is`("Dormition Cathedral, Kyiv II"))
            MatcherAssert.assertThat(photosResponse!!.photos!!.photos!![0].server, `is`("65535"))
            MatcherAssert.assertThat(photosResponse!!.photos!!.photos!![0].id, `is`("50663569263"))
        }
    }
}