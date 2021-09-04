package org.dark0ghost.tinkoff_app_test.api_developerslife

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class DevelopersLifeApi(private val basicUrlApi: String,private val client: HttpClient ): GetGifFromSite {

    private val cache: MutableList<DataForRender> = mutableListOf()
    private var counterRandom: Int = 0

    private fun MutableList<DataForRender>.extend(dataForRenders: List<DataForRender>) {
        dataForRenders.forEach {
            this@DevelopersLifeApi.cache.add(it)
        }
    }

    private suspend fun getRandomJson(): ListDataForRender {
        return client.get("$basicUrlApi/latest/0?json=true")
    }

    private suspend fun getTopPageJson(page: Int = 0): String =
        client.get("$basicUrlApi/top/$page?json=true")

    private suspend fun getHotPageJson(page: Int = 0): String =
        client.get("$basicUrlApi/hot/$page?json=true")

    override suspend fun getTopGif() {
        val response: HttpResponse = client.get("$basicUrlApi/latest/0?json=true")

    }

    override suspend fun getHotGif() {
        TODO("Not yet implemented")
    }

    override suspend fun getRandomGif(): DataForRender {
        if (counterRandom == cache.size) {
            cache.extend(getRandomJson().result)
        }
        val res = cache[counterRandom]
        counterRandom++
        return res
    }

    override fun getBackRandomGif(): DataForRender {
        if (counterRandom <= 0) {
            return cache[0]
        }
        val res = cache[counterRandom]
        counterRandom--
        return res
    }

    data class Builder(
        var url: String = "https://developerslife.ru",
        var engineFactory: HttpClientEngineFactory<HttpClientEngineConfig> = CIO
    ) {
        fun <T : HttpClientEngineConfig> setEngine(engine: HttpClientEngineFactory<T>) = apply {
            engineFactory = engine
        }

        fun setUrl(text: String) = apply {
            url = text
        }

        fun build(): DevelopersLifeApi = DevelopersLifeApi(url, HttpClient(engineFactory) {
            install(JsonFeature)
        })
    }
}