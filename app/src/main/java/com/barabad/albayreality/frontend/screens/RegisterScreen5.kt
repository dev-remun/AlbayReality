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
import com.barabad.albayreality.backend.FirebaseAuthManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.barabad.albayreality.ui.theme.primary
import com.barabad.albayreality.ui.theme.strokes
import com.barabad.albayreality.frontend.components.Button
import com.barabad.albayreality.frontend.components.InputField
import com.barabad.albayreality.frontend.components.PasswordInputField
import com.barabad.albayreality.frontend.components.PopUp
import com.barabad.albayreality.frontend.utilities.data.user_registration.UserRegistrationInformations
import com.barabad.albayreality.ui.theme.TitanOne
import com.barabad.albayreality.R

@Composable
fun RegisterScreen5(navController: NavController, user_registration_info_object: UserRegistrationInformations) {

    // # firebase variable
    val auth_register = FirebaseAuthManager()

    // # state variables for inputs
    var email by remember { mutableStateOf(user_registration_info_object.user_registration_info.email) }
    var password by remember { mutableStateOf(user_registration_info_object.user_registration_info.password) }
    var confirm_password by remember { mutableStateOf("") }

    // # state variables to detect errors
    var email_error by remember { mutableStateOf(false) }
    var password_error by remember { mutableStateOf(false) }
    var confirm_password_error by remember { mutableStateOf(false) }

    // # state variables for custom error messages
    var email_error_message by remember { mutableStateOf("") }
    var password_error_message by remember { mutableStateOf("") }
    var confirm_password_error_message by remember { mutableStateOf("") }

    // # state variable to manage loading status
    var is_loading by remember { mutableStateOf(false) }

    // # state variable to control the success popup
    var display_success_popup by remember { mutableStateOf(false) }

    // # state variable to control the error popup
    var display_error_popup by remember { mutableStateOf(false) }

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
            // # outline text
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
            // # fill text
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

        // # register form
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
                    text = "Please input your account information",
                    color = strokes.copy(alpha = 0.80f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(32.dp))

                // # email field
                InputField(
                    title = "Email",
                    value = email,
                    onValueChange = {
                        email = it
                        if (email_error) email_error = false
                    },
                    placeholder = "Enter your email",
                    has_error = email_error,
                    error_message = email_error_message
                )

                Spacer(modifier = Modifier.height(6.dp))

                // # password field
                PasswordInputField(
                    title = "Password",
                    value = password,
                    onValueChange = {
                        password = it
                        if (password_error) password_error = false
                    },
                    placeholder = "Enter your password",
                    has_error = password_error,
                    error_message = password_error_message
                )

                Spacer(modifier = Modifier.height(6.dp))

                // # confirm password field
                PasswordInputField(
                    title = "Confirm Password",
                    value = confirm_password,
                    onValueChange = {
                        confirm_password = it
                        if (confirm_password_error) confirm_password_error = false
                    },
                    placeholder = "Re-enter your password",
                    has_error = confirm_password_error,
                    error_message = confirm_password_error_message
                )

                Spacer(modifier = Modifier.height(20.dp))

                // # register button
                Button(
                    text = if (is_loading) "Please wait" else "Register",
                    is_enabled = if (!is_loading) {
                        true
                    } else {
                        false
                    },
                    isPrimary = true,
                    onClick = {
                        // # prevent multiple clicks while loading
                        if (is_loading) return@Button

                        var has_error = false

                        if (email.isBlank()) {
                            email_error_message = "Please input your email."
                            email_error = true
                            has_error = true
                        }
                        if (password.isBlank()) {
                            password_error_message = "Please input your password."
                            password_error = true
                            has_error = true
                        }

                        if (confirm_password.isBlank()) {
                            confirm_password_error_message = "Please re-enter your password."
                            confirm_password_error = true
                            has_error = true
                        }

                        if (confirm_password != password) {
                            confirm_password_error_message = "Passwords does not match."
                            confirm_password_error = true
                            has_error = true
                        }


                        if (!has_error) {

                            // # trigger loading state
                            is_loading = true

                            user_registration_info_object.updateUserRegistrationInformation("email", email)
                            user_registration_info_object.updateUserRegistrationInformation("password", password)

                            Log.d("register_screen5", "Username: $email")
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
                            Log.d("register_screen5", "Username: ${user_registration_info_object.user_registration_info.email}")
                            Log.d("register_screen5", "Password: ${user_registration_info_object.user_registration_info.password}")

                            // # registers the user
                            auth_register.registerUser(email, password, object : FirebaseAuthManager.AuthCallback {
                                override fun onSuccess() {
                                    println("Registration success")

                                    // Initializes the user's register data
                                    val userId = FirebaseAuth.getInstance().currentUser?.uid
                                    val userMap = hashMapOf(
                                        "firstname" to user_registration_info_object.user_registration_info.firstname,
                                        "lastname" to user_registration_info_object.user_registration_info.lastname,
                                        "middlename" to user_registration_info_object.user_registration_info.middlename,
                                        "sex" to user_registration_info_object.user_registration_info.sex,
                                        "birthdate" to "${user_registration_info_object.user_registration_info.birth_year}-${user_registration_info_object.user_registration_info.birth_month}-${user_registration_info_object.user_registration_info.birth_date}",
                                        "region" to user_registration_info_object.user_registration_info.region,
                                        "province" to user_registration_info_object.user_registration_info.province,
                                        "city_municipality" to user_registration_info_object.user_registration_info.city_municipality,
                                        "email" to user_registration_info_object.user_registration_info.email
                                    )

                                    // Register the user's data into firestore
                                    FirebaseFirestore.getInstance()
                                        .collection("users")
                                        .document(userId!!)
                                        .set(userMap)
                                        .addOnSuccessListener {
                                            // # reset loading state and show success popup
                                            is_loading = false
                                            display_success_popup = true
                                        }
                                        .addOnFailureListener { e ->
                                            // # reset loading state and show error if firestore fails
                                            is_loading = false
                                            display_error_popup = true
                                            Log.e("register_screen5", "firestore error: ${e.message}")
                                        }
                                }

                                override fun onFailure(errorMessage: String?) {
                                    is_loading = false

                                    val error_message_firebase = errorMessage ?: "An unknown error occured."
                                    display_error_popup = true

                                    val check_error_message_firebase = error_message_firebase.lowercase()
                                    if (check_error_message_firebase.contains("email") ||
                                        check_error_message_firebase.contains("address") ||
                                        check_error_message_firebase.contains("taken") ||
                                        check_error_message_firebase.contains("format")) {
                                        email_error = true
                                        email_error_message = error_message_firebase
                                    }
                                    if (check_error_message_firebase.contains("password")) {
                                        password_error = true
                                        password_error_message = "Password should be at least 6 characters."
                                    }

                                    Log.e("register_screen5", "registration error: $errorMessage")
                                }
                            })
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // # login link
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

    // # handle the error popup display
    if (display_error_popup) {
        PopUp(
            icon = R.drawable.xmark_icon,
            message = "Registration Failed. Please try again.",
            button_text = "Try again",
            onButtonClick = {
                display_error_popup = false
            },
            onDismiss = { display_error_popup = false }
        )
    }

    // # display popup on success
    if (display_success_popup) {
        PopUp(
            icon = R.drawable.check_icon,
            message = "Account registered successfully!",
            button_text = "Go to Log In",
            onButtonClick = {
                display_success_popup = false
                navController.navigate("login")
            },
            onDismiss = {
                display_success_popup = true
            }
        )
    }
}