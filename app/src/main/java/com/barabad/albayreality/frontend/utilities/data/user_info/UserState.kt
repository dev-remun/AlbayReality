package com.barabad.albayreality.frontend.utilities.data.user_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UserState : ViewModel() {

    // # actual state variable
    /*var user_data by mutableStateOf<UserModel?>(null)
        private set*/

    // # development
    var user_data by mutableStateOf(MockUserData.current_user)
        private set

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

    fun setCityMun(city_mun: String) {
        user_data.city_mun = city_mun
    }

    fun setEmail(email: String) {
        user_data.email = email
    }

    fun setPassword(password: String) {
        user_data.password = password
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
        return user_data.city_mun
    }

    fun getEmail(): String {
        return user_data.email
    }

    fun getPassword(): String {
        return user_data.password
    }
}