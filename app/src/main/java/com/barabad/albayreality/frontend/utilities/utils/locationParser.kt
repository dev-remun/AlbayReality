package com.barabad.albayreality.frontend.utilities.utils

import com.barabad.albayreality.frontend.utilities.data.CityMunicipalityModel
import com.barabad.albayreality.frontend.utilities.data.ProvinceModel
import com.barabad.albayreality.frontend.utilities.data.RegionModel
import org.json.JSONObject

// # parse region json string and return a list of RegionModel
// # idea: read the json "RECORDS" array and map each object to RegionModel
fun parseRegions(json: String): List<RegionModel> {

    val json_object = JSONObject(json)           // # convert string to JSONObject
    val records = json_object.getJSONArray("RECORDS")  // # get "RECORDS" array from json

    val list = mutableListOf<RegionModel>()      // # list to hold parsed regions

    for (i in 0 until records.length()) {        // # iterate each record
        val item = records.getJSONObject(i)      // # get individual json object

        list.add(
            RegionModel(
                reg_code = item.getString("regCode"),   // # region code
                reg_desc = item.getString("regDesc")    // # region description
            )
        )
    }

    return list    // # return the list of regions
}

// # the idea is the same with the region
fun parseProvinces(json: String): List<ProvinceModel> {

    val json_object = JSONObject(json)
    val records = json_object.getJSONArray("RECORDS")

    val list = mutableListOf<ProvinceModel>()

    for (i in 0 until records.length()) {
        val item = records.getJSONObject(i)

        list.add(
            ProvinceModel(
                prov_code = item.getString("provCode"),
                prov_desc = item.getString("provDesc"),
                reg_code = item.getString("regCode")
            )
        )
    }

    return list   // # return the list of provinces
}

fun parseCities(json: String): List<CityMunicipalityModel> {

    val json_object = JSONObject(json)
    val records = json_object.getJSONArray("RECORDS")

    val list = mutableListOf<CityMunicipalityModel>()

    for (i in 0 until records.length()) {
        val item = records.getJSONObject(i)

        list.add(
            CityMunicipalityModel(
                citymun_desc = item.getString("citymunDesc"),
                prov_code = item.getString("provCode")
            )
        )
    }

    return list   // # return the list of cities
}