package com.barabad.albayreality.frontend.utilities.data.user_info

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.SetOptions

class UserState : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    private fun getUserId(): String? {
        return FirebaseAuth.getInstance().currentUser?.uid
    }
    var viewedSitesMap by mutableStateOf<Map<String, Boolean>>(emptyMap())
        private set
    var user_data by mutableStateOf(UserModel())

    fun fetchUserData() {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        if (uid != null) {
            FirebaseFirestore.getInstance().collection("users").document(uid)
                .get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val fetchedUser = document.toObject(UserModel::class.java)
                        if (fetchedUser != null) {
                            user_data = fetchedUser
                        }
                    }
                }
        }
    }

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
    fun clearUserData() {
        user_data = UserModel()
    }
}
