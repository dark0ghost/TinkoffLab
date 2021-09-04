package org.dark0ghost.tinkoff_app_test.menu_details

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import org.dark0ghost.tinkoff_app_test.api_developerslife.DataForRender
import org.dark0ghost.tinkoff_app_test.api_developerslife.GetGifFromSite


@Composable
fun RenderImage(dataForRender: DataForRender){
    GlideImage(
        imageModel = dataForRender.gifURL.replace("http","https"),
        failure = {
            InetError { contexts: Context, apis: GetGifFromSite ->
                LastScreen(context = contexts, api = apis)
            }
        },
        modifier = Modifier
            .size(300.dp)
            .padding(0.dp),
        shimmerParams = ShimmerParams(
            baseColor = Color.LightGray,
            highlightColor = Color.White,
            durationMillis = 500
        ),
        requestOptions = RequestOptions()
            .override(256, 256)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop(),
        alignment = Alignment.Center,
    )
    // текст проподает на фоне поэтому идет отдельно
    Text(
        text = dataForRender.description,
        textAlign = TextAlign.Center,
        fontSize = 20.sp
    )
}