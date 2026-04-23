package com.barabad.albayreality.frontend.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.barabad.albayreality.frontend.components.Header
import com.barabad.albayreality.frontend.components.NavBar
import com.barabad.albayreality.frontend.components.SubHeaderTypeA
import com.barabad.albayreality.ui.theme.Inter
import com.barabad.albayreality.ui.theme.primary
import com.barabad.albayreality.ui.theme.strokes

@Composable
fun AboutUsScreen(navController: NavController) {

    // # 2 represents the about us tab
    var active_tab by remember { mutableStateOf(2) }

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
                .padding(top = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Header(
                nav_controller = navController,
                title = "About Us"
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {

                // # albay reality description card
                AboutCard(
                    header_text = "Albay Reality",
                    body_text = "Albay Reality is your pocket-sized gateway to Albay’s rich cultural heritage. Using Augmented Reality and QR code scanning, the app turns ordinary places and objects into interactive learning experiences. Simply scan a marker to bring detailed 3D models of landmarks and festivals to life, complete with stories, and fact that reveal their historical and cultural significance."
                )

                Spacer(modifier = Modifier.height(24.dp))

                // # developers description card
                AboutCard(
                    header_text = "Developers",
                    body_text = "We are a five-man team of third-year Computer Science students from Bicol University – College of Science. Our mission is to promote and preserve the rich cultural heritage of Albay by leveraging the power of Augmented Reality (AR) technology. Through our application, we aim to educate and inspire others by bringing historical and cultural sites to life in an interactive and engaging way."
                )

                Spacer(modifier = Modifier.height(32.dp))

                // # developer profile cards
                DeveloperProfileCard(
                    initials = "GJ",
                    name = "Abion, Gerik Jed L.",
                    role = "Database & Backend Developer"
                )

                Spacer(modifier = Modifier.height(12.dp))

                DeveloperProfileCard(
                    initials = "AJ",
                    name = "Acuña, Angelo James M.",
                    role = "Database & Backend Developer"
                )

                Spacer(modifier = Modifier.height(12.dp))

                DeveloperProfileCard(
                    initials = "R",
                    name = "Balingbing, Raymond C.",
                    role = "UI/UX Designer & Frontend Developer"
                )

                Spacer(modifier = Modifier.height(12.dp))

                DeveloperProfileCard(
                    initials = "JJ",
                    name = "Balquin, John Jacob",
                    role = "Quality Assurance Engineer"
                )

                Spacer(modifier = Modifier.height(12.dp))

                DeveloperProfileCard(
                    initials = "EJ",
                    name = "Rivera, Elyzel Jade P.",
                    role = "Project Manager & 3D Modeler"
                )

                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }
}

@Composable
fun AboutCard(header_text: String, body_text: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, strokes, RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
    ) {
        // # card header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(primary)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = header_text,
                style = TextStyle(
                    fontFamily = Inter,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = strokes
                )
            )
        }

        HorizontalDivider(thickness = 1.dp, color = strokes)

        // # card body
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(
                text = body_text,
                textAlign = TextAlign.Justify,
                style = TextStyle(
                    fontFamily = Inter,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                    color = strokes,
                    lineHeight = 20.sp
                )
            )
        }
    }
}

@Composable
fun DeveloperProfileCard(initials: String, name: String, role: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, strokes, RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // # circular avatar with initials
        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .background(primary),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = initials,
                style = TextStyle(
                    fontFamily = Inter,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = strokes
                )
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // # developer name and specific role
        Column {
            Text(
                text = name,
                style = TextStyle(
                    fontFamily = Inter,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = strokes
                )
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = role,
                style = TextStyle(
                    fontFamily = Inter,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = strokes
                )
            )
        }
    }
}