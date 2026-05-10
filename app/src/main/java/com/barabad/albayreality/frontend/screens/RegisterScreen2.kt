package com.barabad.albayreality.frontend.screens

import android.util.Log
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
import com.barabad.albayreality.frontend.components.DropdownField
import com.barabad.albayreality.frontend.components.PopUp
import com.barabad.albayreality.frontend.utilities.data.user_registration.UserRegistrationInformations
import com.barabad.albayreality.frontend.utilities.utils.rememberNetworkStatus
import com.barabad.albayreality.ui.theme.TitanOne

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen2(navController: NavController, user_registration_info_object: UserRegistrationInformations) {

    // # Data sources for dropdowns
    val months = listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
    val dates = (1..31).map { it.toString() }
    val years = (1960..2024).reversed().map { it.toString() }

    // # State variables for inputs
    var birth_month by remember { mutableStateOf(user_registration_info_object.user_registration_info.birth_month) }
    var birthdate by remember { mutableStateOf(user_registration_info_object.user_registration_info.birth_date) }
    var birth_year by remember { mutableStateOf(user_registration_info_object.user_registration_info.birth_year) }

    // # State variables to detect errors in the input fiels
    var has_birth_month_error by remember { mutableStateOf(false) }
    var has_birthdate_error by remember { mutableStateOf(false) }
    var has_birth_year_error by remember { mutableStateOf(false) }

    // # state variables for custom error message
    var birth_month_error_message by remember { mutableStateOf("") }
    var birthdate_error_message by remember { mutableStateOf("") }
    var birth_year_error_message by remember { mutableStateOf("") }

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
                            text = "Page 2 of 5",
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

                    // # Birth Month Dropdown
                    DropdownField(
                        title = "Birthmonth",
                        value = birth_month,
                        options = months,
                        placeholder = "Select Month",
                        isError = has_birth_month_error,
                        errorMessage = birth_month_error_message,
                        onValueChange = {
                                selected_value ->
                            birth_month = selected_value
                            if (has_birth_month_error) has_birth_month_error = false
                        }
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    // # Birthdate Dropdown
                    DropdownField(
                        title = "Birthdate",
                        value = birthdate,
                        options = dates,
                        placeholder = "Select Date",
                        isError = has_birthdate_error,
                        errorMessage = birthdate_error_message,
                        onValueChange = {
                                selected_value ->
                            birthdate = selected_value
                            if (has_birthdate_error) has_birthdate_error = false
                        }
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    // # Birth Year Dropdown
                    DropdownField(
                        title = "Birthyear",
                        value = birth_year,
                        options = years,
                        placeholder = "Select Year",
                        isError = has_birth_year_error,
                        errorMessage = birth_year_error_message,
                        onValueChange = {
                                selected_value ->
                            birth_year = selected_value
                            if (has_birth_year_error) has_birth_year_error = false
                        }
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

                            if (birth_month.isBlank()) {
                                has_birth_month_error = true
                                birth_month_error_message = "Please input your birth month."
                                has_error = true
                            }
                            if (birthdate.isBlank()) {
                                has_birthdate_error = true
                                birthdate_error_message = "Please input your birth date."
                                has_error = true
                            }
                            if (birth_year.isBlank()) {
                                has_birth_year_error = true
                                birth_year_error_message = "Please input your birth year."
                                has_error = true
                            }

                            if (!has_error) {

                                user_registration_info_object.updateUserRegistrationInformation(
                                    "birth_month",
                                    birth_month
                                )
                                user_registration_info_object.updateUserRegistrationInformation(
                                    "birth_date",
                                    birthdate
                                )
                                user_registration_info_object.updateUserRegistrationInformation(
                                    "birth_year",
                                    birth_year
                                )

                                Log.d(
                                    "register_screen2",
                                    "First Name: ${user_registration_info_object.user_registration_info.firstname}"
                                )
                                Log.d(
                                    "register_screen2",
                                    "Middle Name: ${user_registration_info_object.user_registration_info.middlename}"
                                )
                                Log.d(
                                    "register_screen2",
                                    "Last Name: ${user_registration_info_object.user_registration_info.lastname}"
                                )

                                // # navigate to the next form
                                navController.navigate("register3")
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