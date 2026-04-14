package com.barabad.albayreality.frontend.utilities.data.user_registration

// # user registration data model
data class UserRegistrationInfoModel(
    val firstname: String = "",
    val middlename: String = "",
    val lastname: String = "",
    val sex: String = "",
    val birth_month: String = "",
    val birth_date: String = "",
    val birth_year: String = "",
    val region: String = "",
    val province: String = "",
    val city_municipality: String = "",
    val email: String = "",
    val password: String = ""
)