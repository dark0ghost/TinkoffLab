package org.dark0ghost.tinkoff_app_test.api_developerslife

interface GetGifFromSite {
    suspend fun getTopGif()
    suspend fun getHotGif()
    suspend fun getRandomGif(): ListDataForRender
}