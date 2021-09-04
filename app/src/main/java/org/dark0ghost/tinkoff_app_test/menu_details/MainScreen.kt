package org.dark0ghost.tinkoff_app_test.menu_details

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import org.dark0ghost.tinkoff_app_test.utils.isNetworkAvailable

@Composable
fun InetError() {
    Column {
        Row {
            Image(
                painter = painterResource(R.drawable.cloud),
                contentDescription = "Contact profile picture",
            )
        }
        Text(text = "произошла ошибка при загрузке данных. Проверьте подключение к сети")
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RenderPage(context: Context){
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

        /*Tab(
            text = { Text("Последние") },
            selected = pagerState.currentPage == 1
        )*/
        Tab(
            text = { Text("Лучшие") },
            selected = pagerState.currentPage == 2,
            onClick = { /* TODO */ },
        )
        Tab(
            text = { Text("Горячие") },
            selected = pagerState.currentPage == 2,
            onClick = { /* TODO */ },
        )

    }

    HorizontalPager(state = pagerState) { page ->

        if(!isNetworkAvailable(context = context)){
            InetError()
        }
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
    }
}

@OptIn(ExperimentalPagerApi::class)
@androidx.compose.Composable
fun TabsContent(tabs: List<TabItemMainMenu>, pagerState: PagerState) {
    HorizontalPager(state = pagerState) { page ->
        tabs[page].screen()
    }
}


@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@androidx.compose.Composable
fun Tabs(tabs: List<TabItemMainMenu>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = White,
        contentColor = White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }) {
        tabs.forEachIndexed { index, tab ->
            LeadingIconTab(
                icon = { Icon(painter = painterResource(id = tab.icon), contentDescription = "") },
                text = { Text(tab.title) },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen() {
    val tabs: List<TabItemMainMenu> = listOf(
        TabItemMainMenu.Last,
        TabItemMainMenu.Top,
        TabItemMainMenu.Hot
    )
    val pagerState = rememberPagerState(pageCount = tabs.size)
    Scaffold {
        Column {
            Tabs(tabs = tabs, pagerState = pagerState)
            TabsContent(tabs = tabs, pagerState = pagerState)
        }
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
            MainScreen()
        }
    }

}