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
import com.barabad.albayreality.frontend.utilities.data.UserRegistrationInformations
import com.barabad.albayreality.ui.theme.TitanOne

@Composable
fun RegisterScreen1(navController: NavController, user_registration_info_object: UserRegistrationInformations) {

    // # State variables for inputs
    var firstname by remember { mutableStateOf(user_registration_info_object.user_registration_info.firstname) }
    var middlename by remember { mutableStateOf(user_registration_info_object.user_registration_info.middlename) }
    var lastname by remember { mutableStateOf(user_registration_info_object.user_registration_info.lastname) }

    // # State variables for error messages
    var has_firstname_error by remember { mutableStateOf(false) }
    var has_middlename_error by remember { mutableStateOf(false) }
    var has_lastname_error by remember { mutableStateOf(false) }

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

                // # Firstname Field
                InputField(
                    title = "First Name",
                    value = firstname,
                    onValueChange = {
                        firstname = it
                        if (has_firstname_error) has_firstname_error = false
                    },
                    placeholder = "Juan",
                    has_error = has_firstname_error,
                    error_message = "Please input your first name"
                )

                Spacer(modifier = Modifier.height(6.dp))

                // # Middlename Field (optional)
                InputField(
                    title = "Middle Name",
                    value = middlename,
                    onValueChange = { middlename = it },
                    placeholder = "Santos (optional)",
                    has_error = has_middlename_error,
                    error_message = "Please input your middle name"
                )

                Spacer(modifier = Modifier.height(6.dp))

                // # Lastname Field
                InputField(
                    title = "Last Name",
                    value = lastname,
                    onValueChange = {
                        lastname = it
                        if (has_lastname_error) has_lastname_error = false
                    },
                    placeholder = "Dela Cruz",
                    has_error = has_lastname_error,
                    error_message = "Please input your last name"
                )

                Spacer(modifier = Modifier.height(20.dp))

                // # Register Button
                Button(
                    text = "Next",
                    isPrimary = true,
                    onClick = {
                        var has_error = false

                        if (firstname.isBlank()) {
                            has_firstname_error = true
                            has_error = true
                        }

                        if (lastname.isBlank()) {
                            has_lastname_error = true
                            has_error = true
                        }

                        if (!has_error) {

                            // # Substitute the "NA" value for the middlename if the user did not enter an input
                            if (middlename.isBlank()) {
                                middlename = "NA"
                            }

                            user_registration_info_object.updateUserRegistrationInformation("firstname", firstname)
                            user_registration_info_object.updateUserRegistrationInformation("middlename", middlename)
                            user_registration_info_object.updateUserRegistrationInformation("lastname", lastname)

                            Log.d("register_screen", "Firstname: $firstname")
                            Log.d("register_screen", "Middlename: $middlename")
                            Log.d("register_screen", "Lastname: $lastname")

                            // TODO: navigate to next step
                            navController.navigate("register2")
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