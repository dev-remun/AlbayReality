package com.barabad.albayreality.frontend.utilities.data

// # Region model
data class RegionModel(
    val reg_code: String,
    val reg_desc: String
)

// # Province model
data class ProvinceModel(
    val prov_code: String,
    val prov_desc: String,
    val reg_code: String
)

// # City / Municipality model
data class CityMunicipalityModel(
    val citymun_desc: String,
    val prov_code: String
)