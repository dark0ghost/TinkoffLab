package org.dark0ghost.tinkoff_app_test.menu_details

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import org.dark0ghost.tinkoff_app_test.api_developerslife.DataForRender
import org.dark0ghost.tinkoff_app_test.api_developerslife.GetGifFromSite


@Composable
fun RenderImage(context: Context, api: GetGifFromSite, dataForRenderFn: (index: Int) -> DataForRender) {
    var count by remember { mutableStateOf(0) }

    val dataForRender: DataForRender = dataForRenderFn(count)

    CoilImage(
        imageModel = dataForRender.gifURL.replace("http", "https"),
        failure = {
            InetError(context, api) { contexts: Context, apis: GetGifFromSite ->
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
        alignment = Alignment.Center,
    )
    // текст проподает на фоне поэтому идет отдельно
    Text(
        text = dataForRender.description,
        textAlign = TextAlign.Center,
        fontSize = 20.sp
    )

    Row(modifier = Modifier.padding(all = 8.dp)) {
        if (count != 0) {
            IconButton(modifier = Modifier.then(Modifier.size(30.dp))
                .padding(0.dp, 0.dp, 10.dp, 0.dp),
                onClick = { count-- }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    "contentDescription",
                    tint = Color.Blue
                )
            }
        } else {
            IconButton(modifier = Modifier.then(Modifier.size(30.dp))
                .padding(0.dp, 0.dp, 10.dp, 0.dp),
                enabled = false,
                onClick = { }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    "contentDescription",
                    tint = Color.Blue
                )
            }
        }
        Spacer(modifier = Modifier.width(100.dp))
        IconButton(modifier = Modifier.then(Modifier.size(30.dp))
            .padding(0.dp, 0.dp, 0.dp, 10.dp),
            onClick = { count++ }) {
            Icon(
                Icons.Filled.ArrowForward,
                "contentDescription",
                tint = Color.Blue
            )
        }
    }
}