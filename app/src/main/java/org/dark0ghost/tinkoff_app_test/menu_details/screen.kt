package org.dark0ghost.tinkoff_app_test.menu_details

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.glide.LocalGlideRequestOptions
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.dark0ghost.tinkoff_app_test.R
import org.dark0ghost.tinkoff_app_test.api_developerslife.GetGifFromSite
import org.dark0ghost.tinkoff_app_test.api_developerslife.ListDataForRender
import org.dark0ghost.tinkoff_app_test.utils.isNetworkAvailable

typealias ComposableFun = @Composable (context: Context, api: GetGifFromSite) -> Unit

@Composable
fun InetError(frame: ComposableFun) {
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
                            // frame()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)) {
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
    if(isNetworkAvailable(context = context)) {
        /*var res: ListDataForRender //= ListDataForRender(listOf(DataForRender("test","test")))
        runBlocking {
                res = api.getRandomGif()
        }*/
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {

        }
    }else{
        InetError @Composable{ contexts: Context, apis: GetGifFromSite -> TopScreen(context = contexts, api = apis) }
    }
}

@Composable
fun HotScreen(context: Context, api: GetGifFromSite){
    if(isNetworkAvailable(context = context)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = "Music View",
                textAlign = TextAlign.Center,
                fontSize = 25.sp
            )
        }
    }else{
        InetError @Composable{ contexts: Context, apis: GetGifFromSite -> HotScreen(context = contexts, api = apis) }
    }
}


@Composable
fun LastScreen(context: Context, api: GetGifFromSite) {
    if(isNetworkAvailable(context = context)) {
        var res: ListDataForRender //= ListDataForRender(listOf(DataForRender("test","test")))
        runBlocking {
            res = api.getRandomGif()
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
                GlideImage(
                    imageModel = "https://static.devli.ru/public/images/gifs/201306/b0177891-b012-4a53-9751-672facbd1c6d.gif",
                    failure = {
                        InetError { contexts: Context, apis: GetGifFromSite ->
                            LastScreen(context = contexts, api = apis)
                        }
                    },
                    shimmerParams = ShimmerParams(
                        baseColor = Color.LightGray,
                        highlightColor = White,
                        durationMillis = 500
                    ),
                    requestOptions = RequestOptions()
                        .override(256, 256)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop(),
                    alignment = Alignment.Center,
                )
        }
    }else{
        InetError @Composable{ contexts: Context, apis: GetGifFromSite -> LastScreen(context = contexts, api = apis) }
    }
}
