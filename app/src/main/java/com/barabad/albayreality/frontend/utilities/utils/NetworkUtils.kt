package com.barabad.albayreality.frontend.utilities.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

@Composable
fun rememberNetworkStatus(): State<Boolean> {
    val context = LocalContext.current
    val connectivity_manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    // # check the initial network state before the listener attaches
    fun check_initial_connection(): Boolean {
        val network = connectivity_manager.activeNetwork ?: return false
        val capabilities = connectivity_manager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    // # state variable to hold current connectivity status
    val is_connected = remember { mutableStateOf(check_initial_connection()) }

    DisposableEffect(connectivity_manager) {
        val network_callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                is_connected.value = true
            }

            override fun onLost(network: Network) {
                is_connected.value = false
            }
        }

        val network_request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        // # start listening for network changes
        connectivity_manager.registerNetworkCallback(network_request, network_callback)

        onDispose {
            // # clean up the listener when the composable leaves the screen
            connectivity_manager.unregisterNetworkCallback(network_callback)
        }
    }

    return is_connected
}