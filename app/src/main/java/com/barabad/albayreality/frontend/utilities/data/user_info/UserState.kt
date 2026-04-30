package com.barabad.albayreality.frontend.utilities.data.user_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UserState : ViewModel() {

    var user_data by mutableStateOf(MockUserData.current_user)

    fun setFirstName(firstname: String) {
        user_data.firstname = firstname
    }

    fun setMiddleName(middlename: String) {
        user_data.middlename = middlename
    }

    fun setLastName(lastname: String) {
        user_data.lastname = lastname
    }

    fun setRegion(region: String) {
        user_data.region = region
    }

    fun setProvince(province: String) {
        user_data.province = province
    }

    fun setCityMun(city_municipality: String) {
        user_data.city_municipality = city_municipality
    }

    fun setEmail(email: String) {
        user_data.email = email
    }

    fun setPassword(password: String) {
        user_data.password = password
    }

    fun isLocationSiteViewed(site_id: String): Boolean {
        if (site_id == "st_john_church") {
            return user_data.is_st_john_church_viewed
        } else if (site_id == "cagsawa_church") {
            return user_data.is_cagsawa_church_viewed
        } else if (site_id == "old_albay_hall") {
            return user_data.is_old_albay_hall_viewed
        } else if (site_id == "site_four") {
            return user_data.is_site_four_viewed
        } else if (site_id == "site_five") {
            return user_data.is_site_five_viewed
        } else if (site_id == "site_six") {
            return user_data.is_site_six_viewed
        } else {
            return false
        }
    }

    fun setLocationSiteViewed(site_id: String) {
        if (site_id == "st_john_church") {
            user_data.is_st_john_church_viewed = true
        } else if (site_id == "cagsawa_church") {
            user_data.is_cagsawa_church_viewed = true
        } else if (site_id == "old_albay_hall") {
            user_data.is_old_albay_hall_viewed = true
        } else if (site_id == "site_four") {
            user_data.is_site_four_viewed = true
        } else if (site_id == "site_five") {
            user_data.is_site_five_viewed = true
        } else if (site_id == "site_six") {
            user_data.is_site_six_viewed = true
        }
    }

    fun getFirstName(): String {
        return user_data.firstname
    }

    fun getMiddleName(): String {
        return user_data.middlename.first().toString()
    }

    fun getLastName(): String {
        return user_data.lastname
    }

    fun getRegion(): String {
        return user_data.region
    }

    fun getProvince(): String {
        return user_data.province
    }

    fun getCityMun(): String {
        return user_data.city_municipality
    }

    fun getEmail(): String {
        return user_data.email
    }

    fun getPassword(): String {
        return user_data.password
    }
}