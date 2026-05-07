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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.barabad.albayreality.frontend.components.Button
import com.barabad.albayreality.frontend.components.InputField
import com.barabad.albayreality.frontend.components.PasswordInputField
import com.barabad.albayreality.frontend.components.PopUp
import com.barabad.albayreality.R
import com.barabad.albayreality.backend.FirebaseAuthManager
import com.barabad.albayreality.frontend.utilities.data.user_info.UserState
import com.barabad.albayreality.ui.theme.TitanOne
import com.barabad.albayreality.ui.theme.primary
import com.barabad.albayreality.ui.theme.strokes
import kotlinx.coroutines.delay

@Composable
fun LogInScreen(
    navController: NavController,
    user_state: UserState
) {

    // # firebase variable
    val authLogin = FirebaseAuthManager()
    val firebaseManager = FirebaseAuthManager()

    // # state variables for inputs
    var email_input by remember { mutableStateOf("") }
    var password_input by remember { mutableStateOf("") }

    // # state variables to detect errors
    var has_email_error by remember { mutableStateOf(false) }
    var has_password_error by remember { mutableStateOf(false) }

    // # state variable for error message
    var email_error_message by remember { mutableStateOf("") }
    var password_error_message by remember { mutableStateOf("") }

    // # state variable to control the success popup
    var display_successs_popup by remember { mutableStateOf(false) }

    // # state variable to control the error popup
    var display_error_popup by remember { mutableStateOf(false) }

    // # state variable to manage loading status
    var is_loading by remember { mutableStateOf(false) }

    // # handle the 2-second delay and navigation for success
    if (display_successs_popup) {
        // # launched effect runs when display_popup becomes true
        LaunchedEffect(Unit) {
            delay(2000) // # 2 seconds delay
            display_successs_popup = false
            navController.navigate("home") {
                // # pop up to the login screen to prevent user from going back to log in
                popUpTo("login") { inclusive = true }
            }
        }

        PopUp(
            icon = R.drawable.check_icon,
            message = "Login Successful!",
            button_text = "Proceeding...",
            onButtonClick = {
                navController.navigate("home")
            },
            onDismiss = { display_successs_popup = true }
        )
    }

    // # handle the error popup display
    if (display_error_popup) {
        PopUp(
            icon = R.drawable.xmark_icon,
            message = "Log In Failed. Invalid credentials.",
            button_text = "Try again",
            onButtonClick = {
                display_error_popup = false
            },
            onDismiss = { display_error_popup = false }
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
                .weight(0.25f),
            contentAlignment = Alignment.Center
        ) {
            // # outline text for app title
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
            // # fill text for app title
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

        // # login form
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
                    text = "Login",
                    color = strokes,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "Please input your credentials",
                    color = strokes.copy(alpha = 0.80f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(32.dp))

                // # email input field
                InputField(
                    title = "Email",
                    value = email_input,
                    onValueChange = {
                        email_input = it
                        if (has_email_error) has_email_error = false
                    },
                    placeholder = "Enter your email",
                    has_error = has_email_error,
                    error_message = email_error_message
                )

                Spacer(modifier = Modifier.height(12.dp))

                // # password input field
                PasswordInputField(
                    title = "Password",
                    value = password_input,
                    onValueChange = {
                        password_input = it
                        if (has_password_error) has_password_error = false
                    },
                    placeholder = "Enter your password",
                    has_error = has_password_error,
                    error_message = password_error_message
                )

                Spacer(modifier = Modifier.height(48.dp))

                // # login button
                Button(
                    text = if (is_loading) "Please wait" else "Login",
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

                        if (email_input.isBlank()) {
                            has_email_error = true
                            email_error_message = "Please input your email."
                            has_error = true
                        }
                        if (password_input.isBlank()) {
                            has_password_error = true
                            password_error_message = "Please input your password."
                            has_error = true
                        }

                        if (!has_error) {
                            // # trigger loading state
                            is_loading = true

                            Log.d("log_in_screen", "email: $email_input")
                            Log.d("log_in_screen", "password: $password_input")

                            authLogin.loginUser(email_input, password_input, object : FirebaseAuthManager.AuthCallback {

                                override fun onSuccess() {
                                    // # reset loading state and show success popup
                                    user_state.loadUserViewedSites()
                                    firebaseManager.seedAllQuizzes()
                                    is_loading = false
                                    display_successs_popup = true
                                    navController.navigate("home")
                                }

                                override fun onFailure(errorMessage: String?) {
                                    // # reset loading state and trigger error popup on failure
                                    is_loading = false
                                    display_error_popup = true
                                    Log.e("log_in_screen", "login error: $errorMessage")
                                }
                            })
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // # register link
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Row {
                        Text(
                            text = "Don't have an account? ",
                            color = strokes,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )

                        Text(
                            text = "Register",
                            color = primary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            modifier = Modifier.clickable {
                                navController.navigate("register1")
                            }
                        )
                    }
                }
            }
        }
    }
}