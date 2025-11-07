package com.barabad.albayreality.screens

import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.barabad.albayreality.components.Footer
import com.barabad.albayreality.components.Header
import com.journeyapps.barcodescanner.CompoundBarcodeView



@Composable
fun ArScreen(navController: NavController) {
    var qrCodeValue by remember { mutableStateOf<String?>(null) }
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
                .background(Color(0xFFFFF9C4)),
            contentAlignment = Alignment.Center
        ) {
            if(qrCodeValue == null) {
                QrCodeScanner {
                    qrCodeValue = null
                }
                Text(
                    text = "Error, Please try again",
                    color = Color.Black
                )
            } else if (qrCodeValue!!.contains("albayreality")){
                Text(
                    text = "Scanned QR Code: $qrCodeValue, please insert the corresponding model nalang, maybe sa other screen ulit",
                    color = Color.Black
                )
            }
        }
    }
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
        onResult = { granted ->
            hasCameraPermission = granted
        }
    )

    LaunchedEffect(key1 = true) {
        if (!hasCameraPermission) {
            launcher.launch(android.Manifest.permission.CAMERA)
        }
    }

    if (hasCameraPermission) {
        AndroidView(
            factory = {
                CompoundBarcodeView(it).apply {
                    this.setStatusText("")
                    this.decodeContinuous { result ->
                        result.text?.let { barCodeOrQr->
                            onQrCodeScanned(barCodeOrQr)
                        }
                    }
                }
            },
        )
    }
}