package com.barabad.albayreality.frontend.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barabad.albayreality.frontend.utilities.data.user_registration.UserRegistrationInformations
import com.barabad.albayreality.ui.theme.primary
import com.barabad.albayreality.ui.theme.strokes

@Composable
fun RegistrationProgressIndicator(
    currentStep: Int,
    navController: NavController,
    userInfo: UserRegistrationInformations
) {
    val info = userInfo.user_registration_info

    // # check if the data for each step is present in the saved state object
    val isStep1Valid = info.firstname.isNotBlank() && info.lastname.isNotBlank()
    val isStep2Valid = isStep1Valid && info.birth_month.isNotBlank() && info.birth_date.isNotBlank() && info.birth_year.isNotBlank()
    val isStep3Valid = isStep2Valid && info.sex.isNotBlank()
    val isStep4Valid = isStep3Valid && info.region.isNotBlank() && info.province.isNotBlank() && info.city_municipality.isNotBlank()

    // # determine if a target step is clickable
    fun canNavigateTo(targetStep: Int): Boolean {
        return when (targetStep) {
            1 -> true // # Can always go to step 1
            2 -> isStep1Valid || currentStep >= 2
            3 -> isStep2Valid || currentStep >= 3
            4 -> isStep3Valid || currentStep >= 4
            5 -> isStep4Valid || currentStep >= 5
            else -> false
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (step in 1..5) {
            val isCurrent = step == currentStep
            val canNavigate = canNavigateTo(step)

            Box(
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .size(28.dp)
                    .background(
                        color = if (isCurrent) primary else Color.White,
                        shape = CircleShape
                    )
                    .border(
                        width = 2.dp,
                        color = strokes,
                        shape = CircleShape
                    )
                    .clickable(enabled = canNavigate && !isCurrent) {
                        // # Navigate to the chosen step and avoid building an infinite backstack
                        navController.navigate("register$step") {
                            launchSingleTop = true
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = step.toString(),
                    color = strokes,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }
    }
}