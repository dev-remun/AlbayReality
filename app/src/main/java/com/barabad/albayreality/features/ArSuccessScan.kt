package com.barabad.albayreality.features

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.barabad.albayreality.data.DatabaseProvider
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

        // Main container
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 22.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
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

                    if (location_sites != null && qrContent?.contains("cagsawa") == true) {
                        ModelDisplay("albayrealitycagsawa")
                    }
                    else if (location_sites != null && qrContent?.contains("munisipyo") == true) {
                        ModelDisplay("albayrealitymunisipyo")
                    }
                    else if (location_sites != null && qrContent?.contains("stjohnchurch") == true) {
                        ModelDisplay("albayrealitystjohnchurch")
                    }else {
                        Text("No 3D model found for this QR code. $qrContent")
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                if(location_sites != null) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    ) {
                        Text(
                            text = location_sites.getName().toString(),
                            fontSize = 24.sp,
                            fontFamily = Inter,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "${location_sites.getLocation()}",
                            fontSize = 14.sp,
                            fontFamily = Inter,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black,
                            maxLines = 2
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = location_sites.getDescription().toString(),
                            fontSize = 14.sp,
                            fontFamily = Inter,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF444444)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }

            // Fixed bottom button
            ButtonTypeB(
                text = "Scan Again",
                onClick = {
                    navController.navigate("ar") {
                        popUpTo("ar_success_scan") { inclusive = true }
                        launchSingleTop = true
                    }
                },
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
