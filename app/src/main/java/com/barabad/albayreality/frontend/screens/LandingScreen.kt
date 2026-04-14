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

        Spacer(modifier = Modifier.height(160.dp))

        // # Title
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            // # Outline + Fill Text for "ALBAY"
            Box(contentAlignment = Alignment.Center) {
                // # Outline Text for "ALBAY"
                Text(
                    text = "ALBAY",
                    style = TextStyle(
                        fontSize = 60.sp,
                        fontFamily = TitanOne,
                        fontWeight = FontWeight.Black,
                        color = strokes,
                        drawStyle = Stroke(miter = 10f, width = 12f, join = StrokeJoin.Round)
                    )
                )
                // # Fill Text for "ALBAY"
                Text(
                    text = "ALBAY",
                    style = TextStyle(
                        fontSize = 60.sp,
                        fontFamily = TitanOne,
                        fontWeight = FontWeight.Black,
                        color = primary
                    )
                )
            }

            // # Spacer
            Spacer(modifier = Modifier.height(4.dp))

            // # Outline + Fill Text for "Reality"
            Box(contentAlignment = Alignment.Center) {
                // # Outline Text for "Reality"
                Text(
                    text = "Reality",
                    style = TextStyle(
                        fontSize = 51.sp,
                        fontFamily = TitanOne,
                        fontWeight = FontWeight.Black,
                        color = strokes,
                        drawStyle = Stroke(miter = 10f, width = 12f, join = StrokeJoin.Round)
                    )
                )
                // # Fill Text for "Reality"
                Text(
                    text = "Reality",
                    style = TextStyle(
                        fontSize = 51.sp,
                        fontFamily = TitanOne,
                        fontWeight = FontWeight.Black,
                        color = primary
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // # Subtitle
        Text(
            text = "Exploring Albay, One scan at a time",
            fontFamily = Inter,
            color = strokes,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(0.6f))

        // # Login Button
        Button(
            text = "Login",
            isPrimary = true,
            onClick = { navController.navigate("login") }
        )

        Spacer(modifier = Modifier.height(14.dp))

        // # Register Button
        Button(
            text = "Register",
            isPrimary = false,
            onClick = { navController.navigate("register1") }
        )

        Spacer(modifier = Modifier.weight(0.3f))
    }
}