package org.dark0ghost.tinkoff_app_test.api_developerslife

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*

class DevelopersLifeApi(private val basicUrlApi: String,private val client: HttpClient ): GetGifFromSite {

    private suspend fun getRandomJson(): String = client.get("$basicUrlApi/latest/0?json=true")

    override suspend fun getGif() {//: DataForRender {
        println(getRandomJson())
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

        fun build(): DevelopersLifeApi = DevelopersLifeApi(url, HttpClient(engineFactory))
    }

}