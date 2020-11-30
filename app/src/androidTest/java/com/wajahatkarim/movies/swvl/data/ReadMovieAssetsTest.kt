package com.wajahatkarim.movies.swvl.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.InputStream

@RunWith(AndroidJUnit4::class)
class ReadMovieAssetsTest {

    val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {

    }

    @Test
    fun test_readMoviesFromAssetsFile() {
        // Given
        MatcherAssert.assertThat(context, notNullValue())

        // Invoke
        val inputStream = context.assets.open("movies.json") as InputStream

        // Then
        MatcherAssert.assertThat(inputStream, notNullValue())
    }
}