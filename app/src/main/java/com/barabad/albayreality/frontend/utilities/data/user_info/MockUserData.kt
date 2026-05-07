package com.barabad.albayreality.frontend.utilities.data.user_info

object MockUserData {

    // default mock user for ui testing
    val current_user = UserModel(
        firstname = "Juan",
        middlename = "Bakunawa",
        lastname = "Dela Cruz",
        region = "REGION V (BICOL REGION)",
        province = "Albay",
        city_municipality = "Tabaco City",
        birth_month = "04",
        birth_date = "21",
        birth_year = "2000",
        email = "jdelacruz@test.com",
        password = "password123",
        //is_st_john_church_viewed = false,
        //is_old_albay_hall_viewed = false,
        //is_cagsawa_church_viewed = false,
        is_site_four_viewed = false,
        is_site_five_viewed = false,
        is_site_six_viewed = false
    )
}