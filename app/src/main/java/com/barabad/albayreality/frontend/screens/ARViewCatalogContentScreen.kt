package com.barabad.albayreality.frontend.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barabad.albayreality.R
import com.barabad.albayreality.features.ImageCarousel
import com.barabad.albayreality.frontend.components.Button
import com.barabad.albayreality.frontend.components.Header
import com.barabad.albayreality.frontend.components.NavBar
import com.barabad.albayreality.frontend.components.PopUp
import com.barabad.albayreality.frontend.utilities.data.quizzes.QuizRepository
import com.barabad.albayreality.frontend.utilities.data.user_info.UserState
import com.barabad.albayreality.frontend.utilities.utils.rememberNetworkStatus
import com.barabad.albayreality.ui.theme.Inter
import com.barabad.albayreality.ui.theme.primary
import com.barabad.albayreality.ui.theme.strokes
import com.google.firebase.auth.FirebaseAuth

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

    // # network checking
    val is_connected by rememberNetworkStatus()
    var display_network_popup by remember { mutableStateOf(false) }

    // # automatically show the popup whenever the connection is lost
    LaunchedEffect(is_connected) {
        if (!is_connected) {
            display_network_popup = true
        } else {
            // # automatically hide it if the connection comes back
            display_network_popup = false
        }
    }

    var active_tab by remember { mutableStateOf(-1) }
    var attempt_count by remember { mutableIntStateOf(0) }
    val is_quiz_enabled = attempt_count < 3 && is_connected

    LaunchedEffect(key1 = site_id) {
        user_state.setLocationSiteViewed(site_id)
    }

    LaunchedEffect(key1 = site_id) {
        val user_id = FirebaseAuth.getInstance().currentUser?.uid
        if (user_id != null) {
            val repo = QuizRepository()
            attempt_count = repo.getAttemptCount(user_id, site_id)
        }
    }

    // # display network popup
    if (display_network_popup) {
        PopUp(
            icon = R.drawable.xmark_icon,
            message = "Please connect to Wi-Fi or mobile data to enter Quiz Mode.",
            button_text = "Okay",
            onButtonClick = {
                display_network_popup = false
            },
            onDismiss = {
                display_network_popup = true
            }
        )
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
                        modifier = Modifier.fillMaxWidth(),
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        // # custom box button for quiz mode
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp) // # matched to standard button height
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.White)
                                .border(
                                    BorderStroke(2.dp, strokes),
                                    RoundedCornerShape(8.dp)
                                )
                                .clickable(enabled = is_quiz_enabled) {
                                    // # only trigger the popup if they click it while offline
                                    if (!is_connected) {
                                        display_network_popup = true
                                    } else {
                                        navController.navigate("argame_playground/${site_id}")
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if (is_quiz_enabled) {
                                Text(
                                    text = "Quiz Mode",
                                    style = TextStyle(
                                        fontFamily = Inter,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp,
                                        color = strokes
                                    )
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = "Locked",
                                    tint = primary,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }

                        // # show the orange warning text below the box if limit is reached
                        if (!is_quiz_enabled && is_connected) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "You have reached the maximum 3 attempts",
                                style = TextStyle(
                                    fontFamily = Inter,
                                    fontSize = 12.sp,
                                    color = primary
                                ),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        } else if (!is_connected) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Internet connection required to play",
                                style = TextStyle(
                                    fontFamily = Inter,
                                    fontSize = 12.sp,
                                    color = primary
                                ),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}