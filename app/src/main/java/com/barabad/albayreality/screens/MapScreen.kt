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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.barabad.albayreality.components.Footer
import com.barabad.albayreality.components.Header
import com.barabad.albayreality.features.MapBox
import com.barabad.albayreality.features.TextActivation

@Composable
fun MapScreen(navController: NavController) {

    // State holding the currently selected pin id (null = none)
    var selectedPin by remember { mutableStateOf<String?>(null) }

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = { Header() },
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
                .background(Color.White) // changed background to white
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
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