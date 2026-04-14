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
import com.barabad.albayreality.frontend.utilities.data.historicalsites.HistoricalSiteModel

@Composable
fun MapBox(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(20.dp),
    sites: List<HistoricalSiteModel>,
    is_zoomable: Boolean = true,
    is_scrollable: Boolean = true,
    on_pin_selected: (String) -> Unit
) {
    val context = LocalContext.current

    // # load osmdroid config
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

                    setTileSource(TileSourceFactory.MAPNIK)

                    setMultiTouchControls(is_zoomable)

                    controller.setZoom(10.6)

                    // # set center roughly around albay
                    controller.setCenter(GeoPoint(13.25667, 123.68500))

                    // # loop through the provided sites and plot them dynamically
                    sites.forEach { site ->
                        addInteractivePin(
                            map = this,
                            lat = site.latitude,
                            lon = site.longitude,
                            title = site.title,
                            id = site.site_id,
                            on_pin_selected = on_pin_selected
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

private fun addInteractivePin(
    map: MapView,
    lat: Double,
    lon: Double,
    title: String,
    id: String,
    on_pin_selected: (String) -> Unit
) {
    val marker = Marker(map)
    marker.position = GeoPoint(lat, lon)
    marker.title = title
    marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)

    marker.setOnMarkerClickListener { m, _ ->
        on_pin_selected(id)

        map.controller.animateTo(
            m.position,
            18.0,
            1000L
        )

        true
    }

    map.overlays.add(marker)
}