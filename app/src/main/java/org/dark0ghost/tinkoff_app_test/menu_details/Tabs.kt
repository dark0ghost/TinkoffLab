package org.dark0ghost.tinkoff_app_test.menu_details

import androidx.compose.Composable
import androidx.compose.material.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.ui.tooling.preview.Preview
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import org.dark0ghost.tinkoff_app_test.tab_content.TabItemMainMenu

/*@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(tabs: List<TabItemMainMenu>, pagerState: PagerState) {
    HorizontalPager(state = pagerState) { page ->
        tabs[page].screen()
    }
}


@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
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
@Preview
@Composable
fun TabsPreview() {
    val tabs: List<TabItemMainMenu> = listOf(
        TabItemMainMenu.Last,
        TabItemMainMenu.Top,
        TabItemMainMenu.Hot
    )
    val pagerState = rememberPagerState(pageCount = tabs.size)
    Tabs(tabs = tabs, pagerState = pagerState)
}*/