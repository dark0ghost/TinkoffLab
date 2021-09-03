package org.dark0ghost.tinkoff_app_test.api_developerslife

import androidx.compose.runtime.produceState
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class DevelopersLifeApi(private val basicUrlApi: String,private val client: HttpClient ): GetGifFromSite {

    private suspend fun getRandomJson(): String {
        val res: ListDataForRender = client.get("$basicUrlApi/latest/0?json=true")
        println(res)
        return  ""
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

    override suspend fun getRandomGif() {//: DataForRender {
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

        fun build(): DevelopersLifeApi = DevelopersLifeApi(url, HttpClient(engineFactory){
            install(JsonFeature)
        })
    }

}