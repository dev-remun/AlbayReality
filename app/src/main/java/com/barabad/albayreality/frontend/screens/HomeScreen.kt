package com.barabad.albayreality.frontend.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold // # added scaffold import
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.barabad.albayreality.frontend.components.NavBar // # imported your new navbar
import com.barabad.albayreality.R
import com.barabad.albayreality.frontend.components.ButtonImageA
import com.barabad.albayreality.frontend.components.ButtonImageB
import com.barabad.albayreality.frontend.utilities.data.user_info.UserState
import com.barabad.albayreality.ui.theme.Inter
import com.barabad.albayreality.ui.theme.TitanOne
import com.barabad.albayreality.ui.theme.primary
import com.barabad.albayreality.ui.theme.strokes

@Composable
fun HomeScreen(navController: NavController) {

    var active_tab by remember { mutableStateOf(0) }

    val userState: UserState = viewModel()

    LaunchedEffect(Unit) {
        userState.loadUserViewedSites()
    }

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
                .padding(start = 24.dp, end = 24.dp, top = 60.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                /*Icon(
                    painter = painterResource(id = R.drawable.albayreality_icon),
                    contentDescription = "albay reality logo",
                    tint = primary,
                    modifier = Modifier
                        .size(40.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))*/
                Box() {
                    // # Outline Text
                    Text(
                        text = "Albay Reality",
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontFamily = TitanOne,
                            fontWeight = FontWeight.Black,
                            color = strokes,
                            drawStyle = Stroke(miter = 10f, width = 12f, join = StrokeJoin.Round)
                        )
                    )
                    // # Fill Text
                    Text(
                        text = "Albay Reality",
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontFamily = TitanOne,
                            fontWeight = FontWeight.Black,
                            color = primary
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                Text(
                    text = "Explore",
                    color = strokes,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "Choose your experience",
                    color = strokes.copy(alpha = 0.80f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // # ar scanner button
            ButtonImageA(
                image_res = R.drawable.ar_catalogs_btn_bgimage,
                title = "AR Catalogs",
                on_click = { navController.navigate("catalogs") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // # ar game button
                ButtonImageB(
                    image_res = R.drawable.ar_game_btn_bgimage,
                    title = "Game",
                    on_click = { navController.navigate("games") },
                    modifier = Modifier
                        .weight(1f)
                        .height(200.dp)
                )

                // # interactive map button
                ButtonImageB(
                    image_res = R.drawable.ar_map_btn_bgimage,
                    title = "AR Map",
                    on_click = { navController.navigate("map") },
                    modifier = Modifier
                        .weight(1f)
                        .height(200.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}