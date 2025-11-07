package com.barabad.albayreality.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barabad.albayreality.R
import com.barabad.albayreality.components.CardTypeA
import com.barabad.albayreality.components.CardTypeB
import com.barabad.albayreality.components.Footer
import com.barabad.albayreality.components.Header

@Composable
fun AboutUsScreen(navController: NavController) {
    Scaffold(
        containerColor = Color(0xFFEFEFEF),
        bottomBar = {
            Footer(
                isAboutUsScreen = true,
                leftIsHomeButton = true,
                leftIcon = Icons.Default.Home,
                leftText = "Home",
                rightIcon = Icons.Default.Info,
                onLeftClick = { navController.navigate("home") },
                onRightClick = { /* Already here */ }
            )
        }
    ) { padding ->

        // # Start of About Us Screen
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEFEFEF))
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 22.dp, vertical = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            Header() // # Header

            Spacer(modifier = Modifier.height(16.dp))

            // # About Us Content
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.mask_icon),
                        modifier = Modifier.size(16.dp),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Albay Cultural Heritage",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.DarkGray
                    )
                }

                Spacer(modifier = Modifier.width(31.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Augmented Reality",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ar_icon),
                        modifier = Modifier.size(16.dp),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            // # Albay Reality About
            CardTypeA(
                bardWidth = 92,
                header = "Albay Reality",
                body = "Albay Reality is your pocket-sized gateway to Albay’s rich cultural heritage. Using Augmented Reality and QR code scanning, the app turns ordinary places and objects into interactive learning experiences. Simply scan a marker to bring detailed 3D models of landmarks and festivals to life, complete with stories, and fact that reveal their historical and cultural significance."
            )

            Spacer(modifier = Modifier.height(24.dp))

            // # Developers About
            CardTypeA(
                bardWidth = 81,
                header = "Developers",
                body = "We are a five-man team of third-year Computer Science students from Bicol University – College of Science. Our mission is to promote and preserve the rich cultural heritage of Albay by leveraging the power of Augmented Reality (AR) technology. Through our application, we aim to educate and inspire others by bringing historical and cultural sites to life in an interactive and engaging way."
            )

            Spacer(modifier = Modifier.height(24.dp))

            CardTypeB(
                iconRes = R.drawable.dev_ic,
                title = "Abion, Gerik Jed L.",
                subtitle = "Developer"
            )

            Spacer(modifier = Modifier.height(10.dp))

            CardTypeB(
                iconRes = R.drawable.db_dev_ic,
                title = "Acuña, Angelo James M.",
                subtitle = "Database & Backend Developer"
            )

            Spacer(modifier = Modifier.height(10.dp))

            CardTypeB(
                iconRes = R.drawable.ui_dev_ic,
                title = "Balingbing, Raymond C.",
                subtitle = "Frontend Developer"
            )

            Spacer(modifier = Modifier.height(10.dp))

            CardTypeB(
                iconRes = R.drawable.qa_engr_ic,
                title = "Balquin, John Jacob M.",
                subtitle = "Quality Assurance Engineer"
            )

            Spacer(modifier = Modifier.height(10.dp))

            CardTypeB(
                iconRes = R.drawable.modeler_ic,
                title = "Rivera, Elyzel Jade P.",
                subtitle = "Project Manager & 3D Modeler"
            )

            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}
