package org.dark0ghost.tinkoff_app_test.tab_content

import androidx.compose.Composable


import org.dark0ghost.tinkoff_app_test.R
import org.dark0ghost.tinkoff_app_test.menu_details.HotScreen
import org.dark0ghost.tinkoff_app_test.menu_details.LastScreen
import org.dark0ghost.tinkoff_app_test.menu_details.TopScreen

typealias ComposableFun = @Composable () -> Unit

sealed class TabItemMainMenu(var icon: Int, var title: String, var screen: ComposableFun) {
    object Last : TabItemMainMenu(R.drawable.move, "Последние", { LastScreen() })
    object Top : TabItemMainMenu(R.drawable.top, "Лучшие", { TopScreen() })
    object Hot : TabItemMainMenu(R.drawable.hot, "Горячие", { HotScreen() })
}
