package org.dark0ghost.tinkoff_app_test.menu_details

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.dark0ghost.tinkoff_app_test.R
import org.dark0ghost.tinkoff_app_test.api_developerslife.GetGifFromSite
import org.dark0ghost.tinkoff_app_test.utils.isNetworkAvailable

typealias ComposableFun = @Composable (context: Context, api: GetGifFromSite) -> Unit

@Composable
inline fun CheckNetworkAndRender(context: Context,api: GetGifFromSite, renderFn: @Composable  () -> Unit){
    if(isNetworkAvailable(context = context)) {
        renderFn()
    }else{
        InetError(context, api) @Composable{ contexts: Context, apis: GetGifFromSite -> HotScreen(context = contexts, api = apis) }
    }
}

@Composable
fun InetError(context: Context, api: GetGifFromSite, frame: ComposableFun ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Image(
                    painter = painterResource(R.drawable.cloud),
                    contentDescription = "Contact profile picture",
                )
            }
            Text(
                text = "произошла ошибка при загрузке данных. Проверьте подключение к сети",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
            )
            Button(onClick = {
                          //  frame(context, api)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = White)) {
                Text(
                    text = "повторить",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun TopScreen(context: Context, api: GetGifFromSite){
    CheckNetworkAndRender(context = context, api = api) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                RenderImage(context, api) { index -> runBlocking { api.getTopGif(index) } }
            }
        }
    }
}

@Composable
fun HotScreen(context: Context, api: GetGifFromSite){
    CheckNetworkAndRender(context = context, api = api) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                RenderImage(context, api) { index -> runBlocking { api.getHotGif(index) } }
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun LastScreen(context: Context, api: GetGifFromSite) {
    CheckNetworkAndRender(context = context, api = api) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                RenderImage(context, api) { index -> runBlocking { api.getRandomGif(index) } }
            }
        }
    }
}
