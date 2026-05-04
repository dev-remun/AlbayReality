package com.barabad.albayreality.frontend.utilities.data.user_info

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class UserState : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    private fun getUserId(): String? {
        return FirebaseAuth.getInstance().currentUser?.uid
    }
    var viewedSitesMap by mutableStateOf<Map<String, Boolean>>(emptyMap())
        private set
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

    fun loadUserViewedSites() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId == null) return

        db.collection("users")
            .document(userId)
            .get()
            .addOnSuccessListener { document ->

                Log.d("DEBUG", "Firestore data: ${document.data}")
                val tempMap = mutableMapOf<String, Boolean>()

                val data = document.data
                if (data != null) {
                    for (entry in data) {
                        Log.d("DEBUG", "Key: ${entry.key}, Value: ${entry.value}")
                        if (entry.key.startsWith("viewed_sites.")) {
                            val siteId = entry.key.removePrefix("viewed_sites.")
                            val value = entry.value as? Boolean ?: false
                            tempMap[siteId] = value
                        }
                    }
                }

                viewedSitesMap = tempMap
                Log.d("DEBUG", "Final viewedSitesMap: $tempMap")
            }
    }

    fun isLocationSiteViewed(siteId: String): Boolean {
        return viewedSitesMap[siteId] == true
    }
    fun setLocationSiteViewed(siteId: String) {
        val userId = getUserId() ?: return

        val data = hashMapOf(
            "viewed_sites.$siteId" to true
        )

        db.collection("users")
            .document(userId)
            .set(data, SetOptions.merge())

        viewedSitesMap = viewedSitesMap + (siteId to true)
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

