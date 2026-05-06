package com.barabad.albayreality.frontend.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barabad.albayreality.features.ImageCarousel
import com.barabad.albayreality.frontend.components.Button
import com.barabad.albayreality.frontend.components.Header
import com.barabad.albayreality.frontend.components.NavBar
import com.barabad.albayreality.frontend.utilities.data.user_info.UserState
import com.barabad.albayreality.ui.theme.Inter
import com.barabad.albayreality.ui.theme.strokes

@Composable
fun ARViewCatalogContentScreen(
    navController: NavController,
    site_id: String,
    site_title: String,
    site_location: String,
    site_description: String,
    site_images: List<Int>,
    user_state: UserState
) {

    var active_tab by remember { mutableStateOf(-1) }
    LaunchedEffect(key1 = site_id) {
        user_state.setLocationSiteViewed(site_id)
    }

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
                title = site_title
            )

            // # main content area
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {

                ImageCarousel(images = site_images)

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = site_location,
                    style = TextStyle(
                        fontFamily = Inter,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = strokes
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = site_description,
                    textAlign = TextAlign.Justify,
                    style = TextStyle(
                        fontFamily = Inter,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp,
                        color = strokes,
                        lineHeight = 20.sp
                    )
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    text = "AR Mode",
                    isPrimary = true,
                    onClick = {
                        navController.navigate("armode/${site_id}")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    text = "Quiz Mode",
                    isPrimary = false,
                    onClick = { navController.navigate("argame_playground/${site_id}") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

