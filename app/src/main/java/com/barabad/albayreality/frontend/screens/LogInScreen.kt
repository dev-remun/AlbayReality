package com.barabad.albayreality.frontend.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barabad.albayreality.R
import androidx.compose.ui.res.painterResource
import com.barabad.albayreality.ui.theme.hint_color
import com.barabad.albayreality.ui.theme.inputfield_bg
import com.barabad.albayreality.ui.theme.primary
import com.barabad.albayreality.ui.theme.strokes
import com.barabad.albayreality.frontend.components.Button
import com.barabad.albayreality.frontend.components.InputField
import com.barabad.albayreality.frontend.components.PasswordInputField
import com.barabad.albayreality.ui.theme.TitanOne
import com.barabad.albayreality.ui.theme.error_message_color

@Composable
fun LogInScreen(navController: NavController) {

    // State variables for inputs
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // State variables for error messages
    var username_error by remember { mutableStateOf(false) }
    var password_error by remember { mutableStateOf(false) }

    // Reserve space for error text
    val error_text_height = 18.dp

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

        // # Login Form
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

                // # Username Field
                Column {
                    InputField(
                        title = "Username",
                        value = username,
                        onValueChange = {
                            username = it
                            if (username_error) username_error = false
                        },
                        placeholder = "Enter your username"
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(modifier = Modifier.fillMaxWidth().height(error_text_height)) {
                        if (username_error) {
                            Text(
                                text = "Please input your username",
                                color = error_message_color,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // # Password Field
                Column {
                    PasswordInputField(
                        title = "Password",
                        value = password,
                        onValueChange = {
                            password = it
                            if (password_error) password_error = false
                        },
                        placeholder = "Enter your password"
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(modifier = Modifier.fillMaxWidth().height(error_text_height)) {
                        if (password_error) {
                            Text(
                                text = "Please input your password",
                                color = error_message_color,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(48.dp))

                // # Login Button
                Button(
                    text = "Login",
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

                        if (!hasError) {
                            Log.d("LogInScreen", "Username: $username")
                            Log.d("LogInScreen", "Password: $password")

                            // # For frontend development, i-deretso ko muna sa HomeScreen
                            // navController.navigate("home")
                        }

                        // # Dito logic para i-check if ung username and password is existing sa database natin
                        /*
                        * if(username and password exist) {
                        *   navigate to home screen
                        * }
                        * */
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // # Register Link
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
                                navController.navigate("register")
                            }
                        )
                    }
                }
            }
        }
    }
}