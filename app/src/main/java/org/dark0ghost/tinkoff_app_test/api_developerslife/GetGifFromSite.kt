package org.dark0ghost.tinkoff_app_test.api_developerslife

interface GetGifFromSite {
    suspend fun getTopGif(index: Int): DataForRender
    suspend fun getHotGif(index: Int): DataForRender
    suspend fun getRandomGif(index: Int): DataForRender
}