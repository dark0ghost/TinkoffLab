package org.dark0ghost.tinkoff_app_test

import io.ktor.client.engine.cio.*
import kotlinx.coroutines.runBlocking
import org.dark0ghost.tinkoff_app_test.api_developerslife.DevelopersLifeApi
import org.dark0ghost.tinkoff_app_test.api_developerslife.GetGifFromSite
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ApiTest {

    @Test
    fun testSerializableRandom(): Unit = runBlocking {
        val devApi: GetGifFromSite =
            DevelopersLifeApi.Builder().setUrl("https://developerslife.ru").setEngine(
                CIO
            ).build()

        val res = devApi.getRandomGif(1)
        assert(res.gifURL != "0")
    }

    @Test
    fun testSerializableTop(): Unit = runBlocking {
        val devApi: GetGifFromSite =
            DevelopersLifeApi.Builder().setUrl("https://developerslife.ru").setEngine(
                CIO
            ).build()

        val res = devApi.getTopGif(1)
        assert(res.gifURL != "0")
    }
}