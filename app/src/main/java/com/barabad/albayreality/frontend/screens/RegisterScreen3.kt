package com.barabad.albayreality.frontend.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barabad.albayreality.ui.theme.primary
import com.barabad.albayreality.ui.theme.strokes
import com.barabad.albayreality.frontend.components.Button
import com.barabad.albayreality.frontend.components.DropdownField
import com.barabad.albayreality.frontend.utilities.data.UserRegistrationInformations
import com.barabad.albayreality.ui.theme.TitanOne

@Composable
fun RegisterScreen3(navController: NavController, user_registration_info_object: UserRegistrationInformations) {

    // # Data sources for dropdown
    val sex_options = listOf("Male", "Female")

    // # State variables for inputs
    var sex by remember { mutableStateOf(user_registration_info_object.user_registration_info.sex) }

    // # State variables for error handling
    var has_sex_error by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp, bottom = 40.dp),
            contentAlignment = Alignment.Center
        ) {
            // # Outline Text
            Text(
                text = "Albay Reality",
                style = TextStyle(
                    fontSize = 40.sp,
                    fontFamily = TitanOne,
                    fontWeight = FontWeight.Black,
                    color = strokes,
                    drawStyle = Stroke(miter = 10f, width = 12f, join = StrokeJoin.Round)
                )
            )
            // # Fill Text
            Text(
                text = "Albay Reality",
                style = TextStyle(
                    fontSize = 40.sp,
                    fontFamily = TitanOne,
                    fontWeight = FontWeight.Black,
                    color = primary
                )
            )
        }

        // # Register Form
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.75f)
                .drawBehind {
                    val stroke_width = 4.dp.toPx()
                    drawLine(
                        color = strokes,
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        strokeWidth = stroke_width
                    )
                },
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 32.dp)
            ) {
                Text(
                    text = "Register",
                    color = strokes,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "Please input your personal information",
                    color = strokes.copy(alpha = 0.80f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(32.dp))

                // # Sex Dropdown
                DropdownField(
                    title = "Sex",
                    value = sex,
                    options = sex_options,
                    placeholder = "Select Sex",
                    isError = has_sex_error,
                    errorMessage = "Please select your sex",
                    onValueChange = { selected_value ->
                        sex = selected_value
                        if (has_sex_error) has_sex_error = false
                    }
                )

                Spacer(modifier = Modifier.height(240.dp))

                // # Register Button
                Button(
                    text = "Next",
                    isPrimary = true,
                    onClick = {
                        var has_error = false

                        if (sex.isBlank()) {
                            has_sex_error = true
                            has_error = true
                        }

                        if (!has_error) {
                            user_registration_info_object.updateUserInformation("sex", sex)
                            Log.d("register_screen3", "Sex: $sex")

                            Log.d("register_screen3", "First Name: ${user_registration_info_object.user_registration_info.firstname}")
                            Log.d("register_screen3", "Middle Name: ${user_registration_info_object.user_registration_info.middlename}")
                            Log.d("register_screen3", "Last Name: ${user_registration_info_object.user_registration_info.lastname}")
                            Log.d("register_screen3", "Sex: ${user_registration_info_object.user_registration_info.sex}")
                            Log.d("register_screen3", "Birth Month: ${user_registration_info_object.user_registration_info.birth_month}")
                            Log.d("register_screen3", "Birth Date: ${user_registration_info_object.user_registration_info.birth_date}")
                            Log.d("register_screen3", "Birth Year: ${user_registration_info_object.user_registration_info.birth_year}")

                            // TODO: navigate to next step
                            navController.navigate("register4")
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // # Login Link
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Row {
                        Text(
                            text = "Already have an account? ",
                            color = strokes,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )

                        Text(
                            text = "Login",
                            color = primary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            modifier = Modifier.clickable {
                                navController.navigate("login")
                            }
                        )
                    }
                }
            }
        }
    }
}