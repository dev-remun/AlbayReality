package com.barabad.albayreality.frontend.utilities.utils

import com.barabad.albayreality.frontend.utilities.data.CityMunicipalityModel
import com.barabad.albayreality.frontend.utilities.data.ProvinceModel
import com.barabad.albayreality.frontend.utilities.data.RegionModel

// # map regions, provinces, and cities into a nested structure
// # connect region -> province -> city using their codes
// # output: Map<region_name, Map<province_name, List<city_name>>>
fun mapRegProvCity(
    regions: List<RegionModel>,
    provinces: List<ProvinceModel>,
    cities: List<CityMunicipalityModel>
): Map<String, Map<String, List<String>>> {

    // # final structure that will hold region -> province -> cities
    val location_data = mutableMapOf<String, MutableMap<String, MutableList<String>>>()

    // # group provinces based on region code
    // # result: reg_code -> list of provinces
    val provinces_by_region = provinces.groupBy { it.reg_code }

    // # group cities based on province code
    // # result: prov_code -> list of cities
    val cities_by_province = cities.groupBy { it.prov_code }

    // # loop through each region
    regions.forEach { current_region ->

        val region_name = current_region.reg_desc   // # get region name
        val province_map = mutableMapOf<String, MutableList<String>>() // # holds provinces under this region

        // # get all provinces that belong to this region
        val province_list = provinces_by_region[current_region.reg_code] ?: emptyList()

        // # loop through each province
        province_list.forEach { current_province ->

            val province_name = current_province.prov_desc   // # get province name

            // # get all cities under this province
            val city_list = cities_by_province[current_province.prov_code]
                ?.map { it.citymun_desc.replace("CITY OF ", "") } // # clean city names
                ?.sorted()                                       // # sort alphabetically
                ?.toMutableList()
                ?: mutableListOf()                               // # fallback if empty

            // # assign cities to province
            province_map[province_name] = city_list
        }

        // # assign province map to region
        location_data[region_name] = province_map
    }

    return location_data   // # return full nested mapping
}