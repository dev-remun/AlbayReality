package com.barabad.albayreality.frontend.utilities.data

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class UserRegistrationInformations : ViewModel() {

    // # hold user registration
    var user_registration_info by mutableStateOf(UserRegistrationInfoModel())
        private set

    // # use this to update the global state variable user_registration_info
    fun updateUserRegistrationInformation(field: String, value: String) {
        when(field) {
            "firstname" -> user_registration_info = user_registration_info.copy(firstname = value)
            "lastname" -> user_registration_info = user_registration_info.copy(lastname = value)
            "middlename" -> user_registration_info = user_registration_info.copy(middlename = value)
            "sex" -> user_registration_info = user_registration_info.copy(sex = value)
            "birth_month" -> user_registration_info = user_registration_info.copy(birth_month = value)
            "birth_date" -> user_registration_info = user_registration_info.copy(birth_date = value)
            "birth_year" -> user_registration_info = user_registration_info.copy(birth_year = value)
            "region" -> user_registration_info = user_registration_info.copy(region = value)
            "province" -> user_registration_info = user_registration_info.copy(province = value)
            "city_municipality" -> user_registration_info = user_registration_info.copy(city_municipality = value)
            "username" -> user_registration_info = user_registration_info.copy(username = value)
            "password" -> user_registration_info = user_registration_info.copy(password = value)
        }
    }
}