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
import com.barabad.albayreality.frontend.components.DropdownField
import com.barabad.albayreality.frontend.components.PopUp
import com.barabad.albayreality.frontend.utilities.data.user_registration.UserRegistrationInformations
import com.barabad.albayreality.frontend.utilities.utils.rememberNetworkStatus
import com.barabad.albayreality.ui.theme.TitanOne

@Composable
fun RegisterScreen3(navController: NavController, user_registration_info_object: UserRegistrationInformations) {

    // # Data sources for dropdown
    val gender_options = listOf(
        "Male",
        "Female",
        "Non-binary",
        "Prefer not to say"
    )

    // # State variables for inputs
    var sex by remember { mutableStateOf(user_registration_info_object.user_registration_info.sex) }

    // # State variables to detect errors in the unoput field
    var has_sex_error by remember { mutableStateOf(false) }

    // # state variables for custome error message
    var sex_error_message by remember { mutableStateOf("") }

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
                            text = "Page 3 of 5",
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

                    // # Sex Dropdown
                    DropdownField(
                        title = "Gender",
                        value = sex,
                        options = gender_options,
                        placeholder = "Select Gender",
                        isError = has_sex_error,
                        errorMessage = sex_error_message,
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
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {

                            // # check network connection first
                            if (!is_connected) {
                                display_network_popup = true
                            }

                            var has_error = false

                            if (sex.isBlank()) {
                                has_sex_error = true
                                sex_error_message = "Please input your gender."
                                has_error = true
                            }

                            if (!has_error) {
                                user_registration_info_object.updateUserRegistrationInformation("sex", sex)

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
}