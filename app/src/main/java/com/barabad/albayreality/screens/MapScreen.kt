package com.barabad.albayreality.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.barabad.albayreality.components.Footer
import com.barabad.albayreality.components.Header

@Composable
fun MapScreen(navController: NavController) {
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFC8E6C9)),
            contentAlignment = Alignment.Center
        ) {
            Text("Map Screen", color = Color.Black)
        }
    }
}
