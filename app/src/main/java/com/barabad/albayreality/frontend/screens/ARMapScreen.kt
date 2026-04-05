package com.barabad.albayreality.frontend.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barabad.albayreality.R
import com.barabad.albayreality.features.MapBox
import com.barabad.albayreality.frontend.components.NavBar
import com.barabad.albayreality.frontend.utilities.data.listOfHistoricalSites
import com.barabad.albayreality.ui.theme.TitanOne
import com.barabad.albayreality.ui.theme.primary
import com.barabad.albayreality.ui.theme.strokes

@Composable
fun ARMapScreen(nav_controller: NavController) {

    // # state variable for holding the currently selected pin id
    var selected_pin by remember { mutableStateOf<String?>(null) }

    // # state for the active tab in the bottom navigation
    var active_tab by remember { mutableStateOf(-1) }

    // # find the selected site details dynamically from the list
    val selected_site = listOfHistoricalSites.find { it.site_id == selected_pin }

    Scaffold(
        bottomBar = {
            NavBar(
                active_tab = active_tab,
                on_tab_selected = { selected_index ->
                    active_tab = selected_index
                },
                nav_controller = nav_controller
            )
        }
    ) { inner_padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(inner_padding)
                .padding(start = 24.dp, end = 24.dp, top = 60.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // # top header section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = { nav_controller.popBackStack() },
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.back_icon),
                        contentDescription = "Back",
                        tint = strokes,
                        modifier = Modifier.size(26.dp)
                    )
                }

                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = "AR Map",
                        style = TextStyle(
                            fontSize = 32.sp,
                            fontFamily = TitanOne,
                            fontWeight = FontWeight.Black,
                            color = strokes,
                            drawStyle = Stroke(miter = 10f, width = 12f, join = StrokeJoin.Round)
                        )
                    )
                    Text(
                        text = "AR Map",
                        style = TextStyle(
                            fontSize = 32.sp,
                            fontFamily = TitanOne,
                            fontWeight = FontWeight.Black,
                            color = primary
                        )
                    )
                }
            }

            // # map and dynamic content container
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Tap a pin location",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                // # map container passing the list of sites so it can map out all pins simultaneously
                // # enabled zoom and scroll parameters for map interactivity
                MapBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    sites = listOfHistoricalSites,
                    is_zoomable = true,
                    is_scrollable = true,
                    on_pin_selected = { id -> selected_pin = id }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // # dynamically displays address, title, description, and image if a pin is clicked
                selected_site?.let { site ->
                    Text(
                        text = site.title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = strokes
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = site.location,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = strokes.copy(alpha = 0.7f)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = site.description,
                        fontSize = 14.sp,
                        lineHeight = 22.sp,
                        color = strokes,
                        textAlign = TextAlign.Justify
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // # display the first image in the list if available
                    if (site.images.isNotEmpty()) {
                        Image(
                            painter = painterResource(id = site.images.first()),
                            contentDescription = site.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}