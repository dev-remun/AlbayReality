package com.barabad.albayreality.frontend.screens

import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.barabad.albayreality.R
import com.barabad.albayreality.frontend.components.NavBar
import com.barabad.albayreality.frontend.components.CatalogCard
import com.barabad.albayreality.frontend.components.Header
import com.barabad.albayreality.frontend.components.PopUp
import com.barabad.albayreality.frontend.utilities.data.historicalsites.getListOfHistoricalSites
import com.barabad.albayreality.frontend.utilities.data.quizzes.QuizRepository
import com.barabad.albayreality.frontend.utilities.data.user_info.UserState
import com.barabad.albayreality.frontend.utilities.utils.rememberNetworkStatus
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

@Composable
fun ARGameScreen(
    navController: NavController,
    user_state: UserState
) {
    var attemptCounts by remember { mutableStateOf(mapOf<String, Int>()) }
    var is_loading by remember { mutableStateOf(true) }
    var active_tab by remember { mutableStateOf(-1) }

    // # network checking
    val is_connected by rememberNetworkStatus()
    var display_network_popup by remember { mutableStateOf(false) }

    // # fetch scores only if connected to the internet
    LaunchedEffect(is_connected) {
        if (is_connected) {
            is_loading = true

            // # ensure viewed sites are loaded
            user_state.loadUserViewedSites()

            val userId = FirebaseAuth.getInstance().currentUser?.uid
            if (userId != null) {
                val sites = getListOfHistoricalSites(user_state).map { it.site_id }

                // # fetch all attempt counts simultaneously instead of one by one
                val deferredCounts = sites.map { siteId ->
                    async {
                        siteId to QuizRepository().getAttemptCount(userId, siteId)
                    }
                }

                // # wait for all of them to finish at once
                attemptCounts = deferredCounts.awaitAll().toMap()
            }
            is_loading = false
        } else {
            // # stop loading and show popup if disconnected
            is_loading = false
            display_network_popup = true
        }
    }

    // # display network popup
    if (display_network_popup) {
        PopUp(
            icon = R.drawable.xmark_icon,
            message = "Please connect to Wi-Fi or mobile data to use the app.",
            button_text = "Okay",
            onButtonClick = {
                display_network_popup = false
            },
            onDismiss = {
                display_network_popup = true
            }
        )
    }

    // # scaffold handles the layout for top bars, bottom bars, and floating action buttons
    Scaffold(
        bottomBar = {
            NavBar(
                active_tab = active_tab,
                on_tab_selected = { selected_index ->
                    active_tab = selected_index
                },
                nav_controller = navController
            )
        }
    ) { inner_padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(inner_padding),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = 700.dp)
                    .fillMaxHeight()
                    .padding( top = 24.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Header(
                    nav_controller = navController,
                    title = "Game"
                )

                // # list of kahoot games/quizzes
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                ) {

                    // # generate catalog buttons from data list
                    getListOfHistoricalSites(user_state).forEach { historical_site ->
                        val past_attempts = attemptCounts[historical_site.site_id] ?: 0
                        CatalogCard(
                            title = historical_site.title,
                            catalog_image = historical_site.images[0],
                            button_text = if (is_loading) "Loading..." else "Play",

                            // # requires internet connection to be true to enable the button
                            is_enabled = is_connected
                                    && !is_loading
                                    && user_state.isLocationSiteViewed(historical_site.site_id)
                                    && past_attempts < 3,

                            // # determines what help text is displayed below the button when it's disabled
                            disabled_help_text = when {
                                !is_connected -> "Internet connection required to play"
                                is_loading -> "Fetching your data. Please wait..."
                                !user_state.isLocationSiteViewed(historical_site.site_id) -> "View site information first"
                                else -> "You have reached the maximum 3 attempts"
                            },
                            show_score_button = past_attempts > 0 && is_connected,
                            on_score_click = {
                                navController.navigate("argame_show_score/${historical_site.site_id}/${historical_site.title}")
                            },
                            onClick = { navController.navigate("argame_playground/${historical_site.site_id}") }
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}