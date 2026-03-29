package com.barabad.albayreality.frontend.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barabad.albayreality.ui.theme.Inter
import com.barabad.albayreality.ui.theme.TitanOne
import com.barabad.albayreality.frontend.components.Button
import com.barabad.albayreality.ui.theme.primary
import com.barabad.albayreality.ui.theme.strokes

@Composable
fun LandingScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Top Spacer to push content down
        Spacer(modifier = Modifier.height(160.dp))

        // Title
        Box(contentAlignment = Alignment.Center) {
            // Main Fill Text
            Text(
                text = "ALBAY\nReality",
                style = TextStyle(
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Black,
                    color = primary,
                    textAlign = TextAlign.Center,
                    lineHeight = 60.sp,
                    fontFamily = TitanOne
                )
            )
            // Outline Text
            Text(
                text = "ALBAY\nReality",
                style = TextStyle(
                    fontSize = 64.sp,
                    fontFamily = TitanOne,
                    fontWeight = FontWeight.Black,
                    color = strokes,
                    textAlign = TextAlign.Center,
                    lineHeight = 60.sp,
                    drawStyle = Stroke(
                        miter = 10f,
                        width = 4f,
                        join = StrokeJoin.Round
                    )
                )
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Subtitle
        Text(
            text = "Exploring Albay, One scan at a time",
            fontFamily = Inter,
            color = strokes,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        // Spacer
        Spacer(modifier = Modifier.weight(0.6f))

        // Login Button
        Button(
            text = "Login",
            isPrimary = true,
            onClick = { navController.navigate("login") }
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Register Button
        Button(
            text = "Register",
            isPrimary = false,
            onClick = { navController.navigate("register") }
        )

        // Spacer
        Spacer(modifier = Modifier.weight(0.3f))
    }
}