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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.barabad.albayreality.frontend.components.NavBar
import com.barabad.albayreality.frontend.components.CatalogCard
import com.barabad.albayreality.frontend.components.Header
import com.barabad.albayreality.frontend.utilities.data.historicalsites.getListOfHistoricalSites
import com.barabad.albayreality.frontend.utilities.data.quizzes.QuizRepository
import com.barabad.albayreality.frontend.utilities.data.user_info.UserState
import com.google.firebase.auth.FirebaseAuth


@Composable
fun ARGameScreen(
    navController: NavController,
    user_state: UserState
) {
    var attemptCounts by remember { mutableStateOf(mapOf<String, Int>()) }

    LaunchedEffect(Unit) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return@LaunchedEffect
        val sites = getListOfHistoricalSites(user_state).map { it.site_id }
        val counts = mutableMapOf<String, Int>()
        for (siteId in sites) {
            counts[siteId] = QuizRepository().getAttemptCount(userId, siteId)
        }
        attemptCounts = counts
    }

    LaunchedEffect(Unit) {
        user_state.loadUserViewedSites()
    }
    var active_tab by remember { mutableStateOf(-1) }
    // can the value be passed here kung anong site id ang tig press? para ma build a quiz nalang ako on one screen and not multiple
    // # Eto ung landing ng kahoot game, andito ung different quizzes for each sites
    // # ung need ng site_id is ung sa may actual kahoot na, ung PlayGroundScreen
    // also
    // # scaffold handles the layout for top bars, bottom bars, and floating action buttons
    Scaffold(
        bottomBar = {
            NavBar(
                active_tab = active_tab,
                on_tab_selected = { selected_index ->
                    active_tab = selected_index
                    // # 0 - the active is home
                    // # 1 - the active is profile
                    // # 2 - the active is about us
                    // # -1 - there is no active in the nav bar
                },
                nav_controller = navController
            )
        }
    ) { inner_padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(inner_padding)
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
                    CatalogCard(
                        title = historical_site.title,
                        catalog_image = historical_site.images[0],
                        button_text = "Play",
                        is_enabled = user_state.isLocationSiteViewed(historical_site.site_id)
                                && (attemptCounts[historical_site.site_id] ?: 0) < 3,
                        disabled_help_text = when {
                            !user_state.isLocationSiteViewed(historical_site.site_id) -> "View site information first"
                            else -> "You have reached the maximum 3 attempts"
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
