package com.barabad.albayreality.frontend.utilities.data.historicalsites

// The data structure for a historical site
data class HistoricalSiteModel(
    val site_id: String,
    val title: String,
    val location: String,
    val description: String,
    val images: List<Int>,
    val latitude: Double,
    val longitude: Double,
)