package org.dark0ghost.tinkoff_app_test.menu_details

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import org.dark0ghost.tinkoff_app_test.R
import org.dark0ghost.tinkoff_app_test.api_developerslife.GetGifFromSite
import org.dark0ghost.tinkoff_app_test.tab_content.TabItemMainMenu


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(tabs: List<TabItemMainMenu>, pagerState: PagerState, context: Context, api: GetGifFromSite) {
    HorizontalPager(state = pagerState) { page ->
        tabs[page].screen(context, api)
    }
}

@OptIn(ExperimentalPagerApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
@Composable
fun RenderPage(context: Context,tabs: List<TabItemMainMenu>, pagerState: PagerState  ) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }
    ) {
        tabs.forEachIndexed { index, title ->
           Tab(
                //icon = { Icon(painter = painterResource(id = title.icon), contentDescription = "") },
                text = { Text(title.title) },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }

                }
            )
        }
    }
}

@Composable
fun Profile() {
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
    }
}


@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun MainScreen(api: GetGifFromSite, context: Context) {
    Surface {
        val tabs: List<TabItemMainMenu> = listOf(
            TabItemMainMenu.Last,
            TabItemMainMenu.Top,
            TabItemMainMenu.Hot
        )

        val pagerState = rememberPagerState(pageCount = tabs.size)

        Column {
            Profile()
            RenderPage(context = context, tabs = tabs, pagerState = pagerState)
        }
        LazyColumn{
            TabsContent(tabs = tabs, pagerState = pagerState, context = context, api = api)
        }

    }

}
