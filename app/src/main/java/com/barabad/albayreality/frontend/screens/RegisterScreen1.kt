package com.barabad.albayreality.frontend.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barabad.albayreality.R
import com.barabad.albayreality.ui.theme.primary
import com.barabad.albayreality.ui.theme.strokes
import com.barabad.albayreality.frontend.components.Button
import com.barabad.albayreality.frontend.components.InputField
import com.barabad.albayreality.frontend.components.PopUp
import com.barabad.albayreality.frontend.utilities.data.user_registration.UserRegistrationInformations
import com.barabad.albayreality.frontend.utilities.utils.rememberNetworkStatus
import com.barabad.albayreality.ui.theme.TitanOne

@Composable
fun RegisterScreen1(navController: NavController, user_registration_info_object: UserRegistrationInformations) {

    // # State variables for inputs
    var firstname by remember { mutableStateOf(user_registration_info_object.user_registration_info.firstname) }
    var middlename by remember { mutableStateOf(user_registration_info_object.user_registration_info.middlename) }
    var lastname by remember { mutableStateOf(user_registration_info_object.user_registration_info.lastname) }

    // # State variables to detect errors in the input fields
    var has_firstname_error by remember { mutableStateOf(false) }
    var has_middlename_error by remember { mutableStateOf(false) }
    var has_lastname_error by remember { mutableStateOf(false) }

    // # state variables for custom error messages
    var firstname_error_message by remember { mutableStateOf("") }
    var middlename_error_message by remember { mutableStateOf("") }
    var lastname_error_message by remember { mutableStateOf("") }

    // # this is used to validate if an input is invalid like firstname containes digits or symbols etc etc
    val valid_string_input_regex = Regex("^[a-zA-Z ]+$")

    // # network checking
    val is_connected by rememberNetworkStatus()
    var display_network_popup by remember { mutableStateOf(false) }

    // # automatically show the popup whenever the connection is lost
    LaunchedEffect(is_connected) {
        if (!is_connected) {
            display_network_popup = true
        } else {
            // # automatically hide it if the connection comes back
            display_network_popup = false
        }
    }

    // # display popup
    if (display_network_popup) {
        PopUp(
            icon = R.drawable.xmark_icon,
            message = "Please connect to Wi-Fi or mobile data to register.",
            button_text = "Okay",
            onButtonClick = {
                display_network_popup = false
            },
            onDismiss = {
                display_network_popup = true
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
            
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier
                        .widthIn(max = 500.dp)
                        .fillMaxHeight()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 24.dp, vertical = 32.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = "Register",
                            color = strokes,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(
                            text = "Page 1 of 5",
                            color = strokes.copy(alpha = 0.80f),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            textDecoration = TextDecoration.Underline
                        )
                    }
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
                        error_message = firstname_error_message
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    // # Middlename Field (optional)
                    InputField(
                        title = "Middle Name",
                        value = middlename,
                        onValueChange = {
                            middlename = it
                            if (has_middlename_error) has_middlename_error = false
                        },
                        placeholder = "Santos (optional)",
                        has_error = has_middlename_error,
                        error_message = middlename_error_message
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
                        error_message = lastname_error_message
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // # Register Button
                    Button(
                        text = "Next",
                        isPrimary = true,
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {

                            // # check network connection first
                            if (!is_connected) {
                                display_network_popup = true
                            }

                            var has_error = false

                            if (firstname.isBlank()) {
                                has_firstname_error = true
                                firstname_error_message = "Please input your first name."
                                has_error = true
                            } else if (!valid_string_input_regex.matches(firstname)) {
                                has_firstname_error = true
                                firstname_error_message = "Please input a valid first name."
                                has_error = true
                            }

                            if (!valid_string_input_regex.matches(middlename) && middlename.isNotBlank()) {
                                has_middlename_error = true
                                middlename_error_message = "Please input a valid middle name."
                                has_error = true
                            }

                            if (lastname.isBlank()) {
                                has_lastname_error = true
                                lastname_error_message = "Please input your last name."
                                has_error = true
                            } else if (!valid_string_input_regex.matches(lastname)) {
                                has_lastname_error = true
                                lastname_error_message = "Please input a valid last name."
                                has_error = true
                            }

                            if (!has_error) {

                                // # Substitute the "NA" value for the middlename if the user did not enter an input
                                if (middlename.isBlank()) {
                                    middlename = "NA"
                                }

                                user_registration_info_object.updateUserRegistrationInformation(
                                    "firstname",
                                    firstname
                                )
                                user_registration_info_object.updateUserRegistrationInformation(
                                    "middlename",
                                    middlename
                                )
                                user_registration_info_object.updateUserRegistrationInformation(
                                    "lastname",
                                    lastname
                                )
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
}