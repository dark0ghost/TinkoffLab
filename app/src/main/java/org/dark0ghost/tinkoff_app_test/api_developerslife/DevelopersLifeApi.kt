package org.dark0ghost.tinkoff_app_test.api_developerslife

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*

class DevelopersLifeApi(private val basicUrlApi: String,private val client: HttpClient ): GetGifFromSite {

    private val cacheRandom: MutableList<DataForRender> = mutableListOf()
    private val cacheTop: MutableList<DataForRender> = mutableListOf()
    private val cacheHot: MutableList<DataForRender> = mutableListOf()

    private var countPageTop = 0
    private var countPageHot = 0
    private var countPageRandom = 0

    private fun MutableList<DataForRender>.extend(dataForRenders: List<DataForRender>) {
        dataForRenders.forEach {
            this.add(it)
        }
    }

    private suspend fun getRandomJson(page: Int = 0): ListDataForRender {
        return client.get("$basicUrlApi/latest/$page?json=true")
    }

    private suspend fun getTopPageJson(page: Int = 0): ListDataForRender =
        client.get("$basicUrlApi/top/$page?json=true")

    private suspend fun getHotPageJson(page: Int = 0): ListDataForRender =
        client.get("$basicUrlApi/hot/$page?json=true")

    override suspend fun getTopGif(index: Int): DataForRender  {
        while (index >= cacheTop.size) {
            cacheTop.extend(getTopPageJson(countPageTop).result)
            countPageTop++
        }
        return cacheTop[index]
    }

    override suspend fun getHotGif(index: Int): DataForRender  {
        while (index >= cacheHot.size) {
            cacheHot.extend(getHotPageJson(countPageHot).result)
            countPageHot++
        }
        return cacheHot[index]
    }

    override suspend fun getRandomGif(index: Int): DataForRender {
        while (index >= cacheRandom.size) {
            cacheRandom.extend(getRandomJson(countPageRandom).result)
            countPageRandom++
        }
        return cacheRandom[index]
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