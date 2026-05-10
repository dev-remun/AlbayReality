package com.barabad.albayreality.frontend.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.barabad.albayreality.R
import com.barabad.albayreality.ui.theme.primary
import com.barabad.albayreality.ui.theme.strokes

@Composable
fun NavBar(
    modifier: Modifier = Modifier,
    active_tab: Int,
    on_tab_selected: (Int) -> Unit,
    nav_controller: NavController
) {

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            ),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        color = Color.White
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            NavigationBar(
                modifier = Modifier
                    .widthIn(max = 500.dp)
                    .padding(horizontal = 38.dp),
                containerColor = Color.Transparent,
                tonalElevation = 0.dp
            ) {

                // # 1. home tab
                NavigationBarItem(
                    selected = active_tab == 0,
                    onClick = {
                        on_tab_selected(0)
                        nav_controller.navigate("home") {
                            popUpTo(nav_controller.graph.startDestinationId) {
                                saveState = false
                            }
                            launchSingleTop = true
                            restoreState = false
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.home_icon),
                            contentDescription = "home",
                            modifier = Modifier.size(26.dp)
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = primary,
                        unselectedIconColor = strokes,
                        indicatorColor = Color.Transparent
                    )
                )

                // # 2. profile tab
                NavigationBarItem(
                    selected = active_tab == 1,
                    onClick = {
                        on_tab_selected(1)
                        nav_controller.navigate("profile") {
                            popUpTo(nav_controller.graph.startDestinationId) {
                                saveState = false
                            }
                            launchSingleTop = true
                            restoreState = false
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.profile_icon),
                            contentDescription = "profile",
                            modifier = Modifier.size(26.dp)
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = primary,
                        unselectedIconColor = strokes,
                        indicatorColor = Color.Transparent
                    )
                )

                // # 3. albay reality tab
                NavigationBarItem(
                    selected = active_tab == 2,
                    onClick = {
                        on_tab_selected(2)
                        nav_controller.navigate("aboutus") {
                            popUpTo(nav_controller.graph.startDestinationId) {
                                saveState = false
                            }
                            launchSingleTop = true
                            restoreState = false
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.albayreality_icon),
                            contentDescription = "albay reality",
                            modifier = Modifier.size(28.dp)
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = primary,
                        unselectedIconColor = strokes,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}