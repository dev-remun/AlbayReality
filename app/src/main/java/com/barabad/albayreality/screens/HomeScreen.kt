package com.barabad.albayreality.screens

import androidx.compose.foundation.background
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barabad.albayreality.components.Header
import com.barabad.albayreality.components.Footer
import com.barabad.albayreality.R
import com.barabad.albayreality.components.ButtonTypeA

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF))
    ) {
        // Scrollable content
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Header()

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp, vertical = 10.dp)
            ) {
                Text(
                    text = "Explore",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Choose your experience",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // AR Scanner Button
            ButtonTypeA(
                imageRes = R.drawable.scanner,
                title = "AR Scanner",
                subtitle = "Scan QR Codes to activate Augmented Reality",
                onClick = { navController.navigate("ar") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Interactive Map Button
            ButtonTypeA(
                imageRes = R.drawable.map,
                title = "Interactive Map",
                subtitle = "Discover locations in Albay, Philippines",
                onClick = { navController.navigate("map") }
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Fixed footer
        Footer(
            isHomeScreen = true,
            leftIsHomeButton = true,
            leftIcon = Icons.Default.Home,
            leftText = "Home",
            rightIcon = Icons.Default.Info,
            onLeftClick = { /* Already home */ },
            onRightClick = { navController.navigate("aboutus") }
        )
    }
}
