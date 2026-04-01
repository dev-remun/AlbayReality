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
import com.barabad.albayreality.frontend.components.Button
import com.barabad.albayreality.frontend.components.InputField
import com.barabad.albayreality.frontend.components.PasswordInputField
import com.barabad.albayreality.frontend.components.PopUp
import com.barabad.albayreality.R
import com.barabad.albayreality.ui.theme.TitanOne
import com.barabad.albayreality.ui.theme.primary
import com.barabad.albayreality.ui.theme.strokes
import kotlinx.coroutines.delay

@Composable
fun LogInScreen(navController: NavController) {

    // # state variables for inputs
    var username_input by remember { mutableStateOf("") }
    var password_input by remember { mutableStateOf("") }

    // # state variables to detect errors
    var has_username_error by remember { mutableStateOf(false) }
    var has_password_error by remember { mutableStateOf(false) }

    // # state variable for error message
    var username_error_message by remember { mutableStateOf("") }
    var password_error_message by remember { mutableStateOf("") }

    // # State variable to control the popup
    var display_popup by remember { mutableStateOf(false) }


    // # Handle the 2-second delay and navigation
    if (display_popup) {
        // LaunchedEffect runs when showPopup becomes true
        LaunchedEffect(Unit) {
            delay(2000) // 2 seconds delay
            display_popup = false
            navController.navigate("home") {
                // Pop up to the login screen to prevent user from going back to log in
                popUpTo("login") { inclusive = true }
            }
        }

        PopUp(
            icon = R.drawable.check_icon, // Make sure this exists in res/drawable
            message = "Login Successful!",
            button_text = "Proceeding...",
            onButtonClick = {
                // Optional: Allow manual override to skip delay
                navController.navigate("home")
            },
            onDismiss = { display_popup = false }
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

                // # username input field
                InputField(
                    title = "Username",
                    value = username_input,
                    onValueChange = {
                        username_input = it
                        if (has_username_error) has_username_error = false
                    },
                    placeholder = "Enter your username",
                    has_error = has_username_error,
                    error_message = username_error_message
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
                    text = "Login",
                    isPrimary = true,
                    onClick = {
                        var has_error = false

                        if (username_input.isBlank()) {
                            has_username_error = true
                            username_error_message = "Please input your username."
                            has_error = true
                        }
                        if (password_input.isBlank()) {
                            has_password_error = true
                            password_error_message = "Please input your password."
                            has_error = true
                        }

                        // # call a backend function to verify if the user exist in the backend
                        // # authUser() or something

                        if (!has_error) {
                            Log.d("log_in_screen", "username: $username_input")
                            Log.d("log_in_screen", "password: $password_input")

                            display_popup = true
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