package com.barabad.albayreality.frontend.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.barabad.albayreality.frontend.components.InputField
import com.barabad.albayreality.frontend.components.PasswordInputField
import com.barabad.albayreality.frontend.components.PopUp
import com.barabad.albayreality.frontend.utilities.data.UserRegistrationInformations
import com.barabad.albayreality.ui.theme.TitanOne
import com.barabad.albayreality.R

@Composable
fun RegisterScreen5(navController: NavController, user_registration_info_object: UserRegistrationInformations) {

    // # State variables for inputs
    var username by remember { mutableStateOf(user_registration_info_object.user_registration_info.username) }
    var password by remember { mutableStateOf(user_registration_info_object.user_registration_info.password) }
    var confirm_password by remember { mutableStateOf("") }

    // # State variables for error messages
    var username_error by remember { mutableStateOf(false) }
    var password_error by remember { mutableStateOf(false) }
    var confirm_password_error by remember { mutableStateOf(false) }

    // # State variable to control the popup
    var display_popup by remember { mutableStateOf(false) }

    if (display_popup) {
        PopUp(
            icon = R.drawable.check_icon,
            message = "Successful Registration!",
            button_text = "Go to Login",
            onButtonClick = {
                // # reset the values in the global state object back to "" to avoid getting the previous values when the user register another account
                user_registration_info_object.updateUserRegistrationInformation("firstname", "")
                user_registration_info_object.updateUserRegistrationInformation("middlename", "")
                user_registration_info_object.updateUserRegistrationInformation("lastname", "")
                user_registration_info_object.updateUserRegistrationInformation("sex", "")
                user_registration_info_object.updateUserRegistrationInformation("birth_month", "")
                user_registration_info_object.updateUserRegistrationInformation("birth_date", "")
                user_registration_info_object.updateUserRegistrationInformation("birth_year", "")
                user_registration_info_object.updateUserRegistrationInformation("region", "")
                user_registration_info_object.updateUserRegistrationInformation("province", "")
                user_registration_info_object.updateUserRegistrationInformation("city_municipality", "")
                user_registration_info_object.updateUserRegistrationInformation("username", "")
                user_registration_info_object.updateUserRegistrationInformation("password", "")
                display_popup = false
                navController.navigate("login")
            },
            onDismiss = {
                display_popup = true
            }
        )
    }

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
                    val strokeWidth = 4.dp.toPx()
                    drawLine(
                        color = strokes,
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        strokeWidth = strokeWidth
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
                    text = "Please input your account information",
                    color = strokes.copy(alpha = 0.80f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(32.dp))

                // # Username Field
                InputField(
                    title = "Username",
                    value = username,
                    onValueChange = {
                        username = it
                        if (username_error) username_error = false
                    },
                    placeholder = "Enter your username",
                    has_error = username_error,
                    error_message = "Please input your username"
                )

                Spacer(modifier = Modifier.height(6.dp))

                // # Password Field
                PasswordInputField(
                    title = "Password",
                    value = password,
                    onValueChange = {
                        password = it
                        if (password_error) password_error = false
                    },
                    placeholder = "Enter your password",
                    has_error = password_error,
                    error_message = "Please input your password"
                )

                Spacer(modifier = Modifier.height(6.dp))

                // # Confirm Password Field
                PasswordInputField(
                    title = "Confirm Password",
                    value = confirm_password,
                    onValueChange = {
                        confirm_password = it
                        if (confirm_password_error) confirm_password_error = false
                    },
                    placeholder = "Re-enter your password",
                    has_error = confirm_password_error,
                    error_message = "Passwords do not match"
                )

                Spacer(modifier = Modifier.height(20.dp))

                // # Register Button
                Button(
                    text = "Register",
                    isPrimary = true,
                    onClick = {
                        var hasError = false

                        if (username.isBlank()) {
                            username_error = true
                            hasError = true
                        }
                        if (password.isBlank()) {
                            password_error = true
                            hasError = true
                        }
                        if (confirm_password.isBlank() || confirm_password != password) {
                            confirm_password_error = true
                            hasError = true
                        }

                        if (!hasError) {

                            user_registration_info_object.updateUserRegistrationInformation("username", username)
                            user_registration_info_object.updateUserRegistrationInformation("password", password)

                            Log.d("register_screen5", "Username: $username")
                            Log.d("register_screen5", "Password: $password")

                            Log.d("register_screen5", "First Name: ${user_registration_info_object.user_registration_info.firstname}")
                            Log.d("register_screen5", "Middle Name: ${user_registration_info_object.user_registration_info.middlename}")
                            Log.d("register_screen5", "Last Name: ${user_registration_info_object.user_registration_info.lastname}")
                            Log.d("register_screen5", "Sex: ${user_registration_info_object.user_registration_info.sex}")
                            Log.d("register_screen5", "Birth Month: ${user_registration_info_object.user_registration_info.birth_month}")
                            Log.d("register_screen5", "Birth Date: ${user_registration_info_object.user_registration_info.birth_date}")
                            Log.d("register_screen5", "Birth Year: ${user_registration_info_object.user_registration_info.birth_year}")
                            Log.d("register_screen5", "Region: ${user_registration_info_object.user_registration_info.region}")
                            Log.d("register_screen5", "Province: ${user_registration_info_object.user_registration_info.province}")
                            Log.d("register_screen5", "City/Muni: ${user_registration_info_object.user_registration_info.city_municipality}")
                            Log.d("register_screen5", "Username: ${user_registration_info_object.user_registration_info.username}")
                            Log.d("register_screen5", "Password: ${user_registration_info_object.user_registration_info.password}")
                            println(
                                """
                                === USER REGISTRATION INFO ===
                                First Name: ${user_registration_info_object.user_registration_info.firstname}
                                Middle Name: ${user_registration_info_object.user_registration_info.middlename}
                                Last Name: ${user_registration_info_object.user_registration_info.lastname}
                                Sex: ${user_registration_info_object.user_registration_info.sex}
                                Birth Month: ${user_registration_info_object.user_registration_info.birth_month}
                                Birth Date: ${user_registration_info_object.user_registration_info.birth_date}
                                Birth Year: ${user_registration_info_object.user_registration_info.birth_year}
                                Region: ${user_registration_info_object.user_registration_info.region}
                                Province: ${user_registration_info_object.user_registration_info.province}
                                City/Muni: ${user_registration_info_object.user_registration_info.city_municipality}
                                Username: ${user_registration_info_object.user_registration_info.username}
                                Password: ${user_registration_info_object.user_registration_info.password}
                                =============================
                                """.trimIndent()
                            )

                            display_popup = true
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