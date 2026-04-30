package com.barabad.albayreality.frontend.utilities.data.user_info

data class UserModel(
    var firstname: String = "",
    var middlename: String = "",
    var lastname: String = "",
    var region: String = "",
    var province: String = "",
    var city_municipality: String = "",
    var birth_month: String = "",
    var birth_date: String = "",
    var birth_year: String = "",
    var email: String = "",
    var password: String = "",
    var is_st_john_church_viewed: Boolean = false,
    var is_old_albay_hall_viewed: Boolean = false,
    var is_cagsawa_church_viewed: Boolean = false,
    var is_site_four_viewed: Boolean = false,
    var is_site_five_viewed: Boolean = false,
    var is_site_six_viewed: Boolean = false
)