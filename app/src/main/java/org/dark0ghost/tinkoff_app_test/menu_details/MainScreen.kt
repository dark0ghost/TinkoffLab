package org.dark0ghost.tinkoff_app_test.menu_details

import android.content.Context
import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.graphics.Color.Companion.Yellow
import androidx.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import org.dark0ghost.tinkoff_app_test.R
import org.dark0ghost.tinkoff_app_test.utils.isNetworkAvailable

@Composable
fun InetError() {
    Row {
        Image(
            painter = painterResource(R.drawable.cloud),
            contentDescription = "Contact profile picture",
        )
    }
    Text(text = "произошла ошибка при загрузке данных. Проверьте подключение к сети")
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RenderPage(){
    val pagerState = rememberPagerState(pageCount = 3)

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }

    ) {
        // Add tabs for all of our pages

        Tab(
            text = { Text("12") },
            selected = pagerState.currentPage == 1,
            onClick = { /* TODO */ },
        )
        Tab(
            text = { Text("13") },
            selected = pagerState.currentPage == 2,
            onClick = { /* TODO */ },
        )

    }

    HorizontalPager(state = pagerState) { page ->
        // TODO: page content
    }
}

@Composable
fun Profile(){
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(40.dp))
        Text("By dark0ghost", fontSize = 30.sp)
        //!isNetworkAvailable(this as Context)) InetError()
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun MainScreen() {
    Surface {
        Column {
            Profile()
            RenderPage()
        }
    }

}