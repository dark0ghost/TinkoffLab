package org.dark0ghost.tinkoff_app_test

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.dark0ghost.tinkoff_app_test.api_developerslife.DevelopersLifeApi
import org.dark0ghost.tinkoff_app_test.api_developerslife.GetGifFromSite
import org.dark0ghost.tinkoff_app_test.menu_details.MainScreen


class MainActivity : AppCompatActivity() {

    private final val devApi: GetGifFromSite = DevelopersLifeApi.Builder().build()

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val context = this as Context
        setContent{
            MainScreen(devApi, context)
        }
    }
}