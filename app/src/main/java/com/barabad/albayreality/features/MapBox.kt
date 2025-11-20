package com.barabad.albayreality.features

import android.preference.PreferenceManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@Composable
fun MapBox(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(20.dp),
    onPinSelected: (String) -> Unit
) {
    val context = LocalContext.current

    // Load OSMDroid config
    Configuration.getInstance()
        .load(context, PreferenceManager.getDefaultSharedPreferences(context))

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(350.dp)
            .graphicsLayer {
                clip = true
                this.shape = shape
            }
            .background(Color.White, shape)
    ) {
        AndroidView(
            factory = { ctx ->
                MapView(ctx).apply {

                    // Map Config
                    setTileSource(TileSourceFactory.MAPNIK)
                    setMultiTouchControls(true)
                    controller.setZoom(13.0)
                    controller.setCenter(GeoPoint(13.150, 123.710))

                    // ---- Add Pins With Click ----

                    addInteractivePin(
                        map = this,
                        lat = 13.16613,
                        lon = 123.70116,
                        title = "Cagsawa Ruins",
                        id = "cagsawa",
                        onPinSelected = onPinSelected
                    )

                    addInteractivePin(
                        map = this,
                        lat = 13.138276,
                        lon = 123.734580,
                        title = "Legazpi City Hall",
                        id = "cityhall",
                        onPinSelected = onPinSelected
                    )

                    addInteractivePin(
                        map = this,
                        lat = 13.182389,
                        lon = 123.654583,
                        title = "John the Baptist Church",
                        id = "church",
                        onPinSelected = onPinSelected
                    )
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

// ---------- Helper: adds a clickable + zoomable pin ----------
private fun addInteractivePin(
    map: MapView,
    lat: Double,
    lon: Double,
    title: String,
    id: String,
    onPinSelected: (String) -> Unit
) {
    val marker = Marker(map)
    marker.position = GeoPoint(lat, lon)
    marker.title = title
    marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)

    marker.setOnMarkerClickListener { m, _ ->
        onPinSelected(id)

        map.controller.animateTo(
            m.position,
            18.0,      // 🔥 zoom in CLOSER (was 16)
            1000L
        )

        true
    }

    map.overlays.add(marker)
}