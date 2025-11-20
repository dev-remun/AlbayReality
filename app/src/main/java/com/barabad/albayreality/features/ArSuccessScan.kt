package com.barabad.albayreality.features

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barabad.albayreality.components.ButtonTypeB
import com.barabad.albayreality.ui.theme.Inter

@Composable
fun ArSuccessScan(navController: NavController) {
    //global variable into qr Content (displayed for proof of concept)
    val globeVal: GlobalVar? = LocalContext.current.applicationContext as? GlobalVar
    val qrContent = globeVal?.content

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFEFEFEF)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(60.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "AR Scanner",
                    fontSize = 24.sp,
                    fontFamily = Inter,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "QR Code scanned successfully!",
                    fontSize = 14.sp,
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0x99000000)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Camera area box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(480.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Green.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "Scan Successful!\n<Insert 3D model> $qrContent ",
                    fontFamily = Inter,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFF388E3C), // Dark green
                    lineHeight = 20.sp
                )
                //do cases of 3d model here
            }

            Spacer(modifier = Modifier.height(24.dp))

            ButtonTypeB(
                text = "Scan Again",
                onClick = {
                    navController.navigate("ar") {
                        popUpTo("ar_success_scan") { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
