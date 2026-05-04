package com.barabad.albayreality.frontend.utilities.data.user_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class UserState : ViewModel() {

    var user_data by mutableStateOf(MockUserData.current_user)

    fun setFirstName(firstname: String) {
        user_data = user_data.copy(firstname = firstname)
    }

    fun setMiddleName(middlename: String) {
        user_data = user_data.copy(middlename = middlename)
    }

    fun setLastName(lastname: String) {
        user_data = user_data.copy(lastname = lastname)
    }

    fun setRegion(region: String) {
        user_data = user_data.copy(region = region)
    }

    fun setProvince(province: String) {
        user_data = user_data.copy(province = province)
    }

    fun setCityMun(city_municipality: String) {
        user_data = user_data.copy(city_municipality = city_municipality)
    }

    fun setEmail(email: String) {
        user_data = user_data.copy(email = email)
    }

    fun setPassword(password: String) {
        user_data = user_data.copy(password = password)
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

    //Function to fetch user from firestore
    fun fetchUserData(uid: String, onComplete: () -> Unit = {}) {
        val db = FirebaseFirestore.getInstance()
        db.collection("users").document(uid).get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val data = document.data
                    if (data != null) {
                        val birthdate = data["birthdate"] as? String ?: ""
                        val birthParts = birthdate.split("-")

                        user_data = user_data.copy(
                            firstname = data["firstname"] as? String ?: "",
                            middlename = data["middlename"] as? String ?: "",
                            lastname = data["lastname"] as? String ?: "",
                            region = data["region"] as? String ?: "",
                            province = data["province"] as? String ?: "",
                            city_municipality = data["city_municipality"] as? String ?: "",
                            email = data["email"] as? String ?: "",
                            birth_year = birthParts.getOrNull(0) ?: "",
                            birth_month = birthParts.getOrNull(1) ?: "",
                            birth_date = birthParts.getOrNull(2) ?: ""
                        )
                    }
                }
                onComplete()
            }
            .addOnFailureListener {
                onComplete()
            }
    }
    //Function to update user data in Firestore
    fun updateFirestoreData(uid: String, onComplete: (Boolean) -> Unit) {
        val db = FirebaseFirestore.getInstance()
        val userMap = hashMapOf(
            "firstname" to user_data.firstname,
            "lastname" to user_data.lastname,
            "middlename" to user_data.middlename,
            "region" to user_data.region,
            "province" to user_data.province,
            "city_municipality" to user_data.city_municipality,
            "email" to user_data.email,
            "birthdate" to "${user_data.birth_year}-${user_data.birth_month}-${user_data.birth_date}"
        )

        db.collection("users").document(uid).update(userMap as Map<String, Any>)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }
}
