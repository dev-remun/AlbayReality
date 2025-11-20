package com.barabad.albayreality

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.barabad.albayreality.features.ArFailedScan
import com.barabad.albayreality.features.ArSuccessScan
import com.barabad.albayreality.screens.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_AlbayReality)
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                val navController = rememberNavController()

                NavHost(navController, startDestination = "home") {
                    composable("home") { HomeScreen(navController) }
                    composable("ar") { ArScreen(navController) }
                    composable("map") { MapScreen(navController) }
                    composable("aboutus") { AboutUsScreen(navController) }
                    composable("ar_failed_scan") { ArFailedScan(navController) }
                    composable("ar_success_scan") { ArSuccessScan(navController) }
                }
            }
        }
    }
}
