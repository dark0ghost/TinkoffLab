package org.dark0ghost.tinkoff_app_test.tab_content

import android.content.Context
import androidx.compose.runtime.Composable
import org.dark0ghost.tinkoff_app_test.R
import org.dark0ghost.tinkoff_app_test.api_developerslife.GetGifFromSite
import org.dark0ghost.tinkoff_app_test.menu_details.ComposableFun
import org.dark0ghost.tinkoff_app_test.menu_details.HotScreen
import org.dark0ghost.tinkoff_app_test.menu_details.LastScreen
import org.dark0ghost.tinkoff_app_test.menu_details.TopScreen




sealed class TabItemMainMenu(var icon: Int, var title: String, var screen: ComposableFun) {
     object Last : TabItemMainMenu(R.drawable.move, "Последние", @Composable {context: Context, api: GetGifFromSite -> LastScreen(context, api) })
     object Top : TabItemMainMenu(R.drawable.top, "Лучшие", @Composable {context: Context, api: GetGifFromSite ->  TopScreen(context, api) })
     object Hot : TabItemMainMenu(R.drawable.hot, "Горячие", @Composable {context: Context, api: GetGifFromSite ->  HotScreen(context, api) })
}
