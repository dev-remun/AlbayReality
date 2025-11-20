package com.barabad.albayreality.features

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun TextActivation(selectedPin: String?) {

    when (selectedPin) {
        "cagsawa" -> {
            Text("Address: Cagsawa Ruins", style = MaterialTheme.typography.titleMedium)
            Text("Description: Placeholder for Cagsawa Ruins.")
        }
        "cityhall" -> {
            Text("Address: Legazpi City Hall", style = MaterialTheme.typography.titleMedium)
            Text("Description: Placeholder for City Hall.")
        }
        "church" -> {
            Text("Address: John the Baptist Church, Camalig", style = MaterialTheme.typography.titleMedium)
            Text("Description: Placeholder for Church.")
        }
        else -> {
            Text("Tap a pin to see details.", style = MaterialTheme.typography.titleMedium)
        }
    }
}