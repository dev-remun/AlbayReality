package com.barabad.albayreality.screens

import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.barabad.albayreality.components.Footer
import com.barabad.albayreality.ui.theme.Inter
import com.journeyapps.barcodescanner.CompoundBarcodeView

@Composable
fun ArScreen(navController: NavController) {
    // # Yo etong ui ng screen ang minodify ko and nagdagdag ako ng ui para macatch ung exception
    var qrCodeValue by remember { mutableStateOf<String?>(null) }
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
                .padding(padding)
                .background(Color(0xFFEFEFEF))
                .verticalScroll(scrollState)
                .padding(horizontal = 22.dp)
        ) {

            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = "AR Scanner",
                fontSize = 24.sp,
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Scan a valid AlbayReality QR code.",
                fontSize = 14.sp,
                fontFamily = Inter,
                fontWeight = FontWeight.SemiBold,
                color = Color(0x99000000)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Camera area
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(480.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Black.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                if (qrCodeValue == null) {
                    QrCodeScanner { scannedValue ->
                        qrCodeValue = scannedValue
                    }
                } else {
                    Text(
                        text = "QR Code scanned!",
                        color = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (qrCodeValue == null) {
                /* pass? hahaha */
            } else if (qrCodeValue!!.contains("albayreality") && qrCodeValue!!.contains("cagsawa")) {
                Text(
                    text = "Scanned QR Code: $qrCodeValue\n\nInsert corresponding model of cagsawa in another screen or something.",
                    color = Color.Black
                )
                //ModelDisplay("cagchu")
            } else if (qrCodeValue!!.contains("albayreality") && qrCodeValue!!.contains("munisipyo")) {
                Text(
                    text = "Scanned QR Code: $qrCodeValue\n\nInsert corresponding model of munisipyo in another screen or something.",
                    color = Color.Black
                )
                //ModelDisplay("Munisipyo")
            } else {
                Text(
                    text = "Invalid QR code detected.\nPlease try again.\nThe Scanned QR Code is: $qrCodeValue",
                    color = Color.Red
                )
            }

            Spacer(modifier = Modifier.height(100.dp)) // to give room before footer
        }
    }
}

@Composable
fun ModelDisplay(modelname: String) {
    TODO("Not yet implemented")
}

@Composable
fun QrCodeScanner(onQrCodeScanned: (String) -> Unit) {
    val context = LocalContext.current
    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted -> hasCameraPermission = granted }
    )

    LaunchedEffect(Unit) {
        if (!hasCameraPermission) {
            launcher.launch(android.Manifest.permission.CAMERA)
        }
    }

    if (hasCameraPermission) {
        var barcodeView: CompoundBarcodeView? = null

        AndroidView(
            factory = { ctx ->
                CompoundBarcodeView(ctx).apply {
                    setStatusText("")
                    decodeContinuous { result ->
                        result.text?.let(onQrCodeScanned)
                    }
                    barcodeView = this
                    resume() // # start ng camera
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
        )

        DisposableEffect(Unit) {
            onDispose {
                barcodeView?.pause() // # stop camera kapag umalis ung user sa screen
            }
        }
    }
}
