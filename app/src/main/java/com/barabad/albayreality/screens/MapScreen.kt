package com.barabad.albayreality.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barabad.albayreality.components.Footer
import com.barabad.albayreality.components.Header
import com.barabad.albayreality.features.MapBox
import com.barabad.albayreality.features.TextActivation
import com.barabad.albayreality.ui.theme.Inter

@Composable
fun MapScreen(navController: NavController) {

    // State holding the currently selected pin id (null = none)
    var selectedPin by remember { mutableStateOf<String?>(null) }

    val scrollState = rememberScrollState()

    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = {
            Footer(
                isHomeScreen = false,
                leftIcon = Icons.Default.ArrowBack,
                leftText = "Back",
                rightIcon = Icons.Default.Info,
                onLeftClick = { navController.popBackStack() },
                onRightClick = { navController.navigate("aboutus") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEFEFEF))
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = "Interactive Map",
                fontSize = 24.sp,
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Tap a location pin to see details.",
                fontSize = 14.sp,
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                color = Color(0x99000000)
            )

            Spacer(modifier = Modifier.height(16.dp))
            // Map container: pass callback to receive pin id when tapped
            MapBox(
                modifier = Modifier.fillMaxWidth(),
                onPinSelected = { id -> selectedPin = id }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Delegates rendering of address/description to your component
            TextActivation(selectedPin)

            Spacer(modifier = Modifier.height(80.dp)) // space above footer
        }
    }
}