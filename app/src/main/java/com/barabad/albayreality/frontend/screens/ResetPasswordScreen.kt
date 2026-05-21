package com.barabad.albayreality.frontend.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.barabad.albayreality.R
import com.barabad.albayreality.backend.FirebaseAuthManager
import com.barabad.albayreality.frontend.components.Button
import com.barabad.albayreality.frontend.components.DropdownField
import com.barabad.albayreality.frontend.components.InputField
import com.barabad.albayreality.frontend.components.PasswordInputField
import com.barabad.albayreality.frontend.components.PopUp
import com.barabad.albayreality.frontend.utilities.utils.rememberNetworkStatus
import com.barabad.albayreality.ui.theme.TitanOne
import com.barabad.albayreality.ui.theme.primary
import com.barabad.albayreality.ui.theme.strokes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

enum class ResetPasswordStep {
    ENTER_EMAIL,
    SECURITY_QUESTION
}

@Composable
fun ResetPasswordScreen(navController: NavController) {
    var currentStep by remember { mutableStateOf(ResetPasswordStep.ENTER_EMAIL) }

    // # email step
    var email by remember { mutableStateOf("") }
    var email_error by remember { mutableStateOf(false) }
    var email_error_message by remember { mutableStateOf("") }

    // # security question step
    val months = listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
    val dates = (1..31).map { it.toString() }
    val years = (1960..2024).reversed().map { it.toString() }

    var birth_month by remember { mutableStateOf("") }
    var birth_date by remember { mutableStateOf("") }
    var birth_year by remember { mutableStateOf("") }

    var dob_error by remember { mutableStateOf(false) }
    var dob_error_message by remember { mutableStateOf("") }

    var is_loading by remember { mutableStateOf(false) }
    var display_success_popup by remember { mutableStateOf(false) }
    var display_error_popup by remember { mutableStateOf(false) }
    var error_message_popup by remember { mutableStateOf("") }

    val is_connected by rememberNetworkStatus()

    // # store user data retrieved from firestore
    var stored_birth_date by remember { mutableStateOf("") }
    var user_id by remember { mutableStateOf("") }

    if (display_success_popup) {
        PopUp(
            icon = R.drawable.check_icon,
            message = "A reset link has been sent to your email. Please check your inbox to update your password.",
            button_text = "Back to Login",
            onButtonClick = {
                display_success_popup = false
                navController.navigate("login") {
                    popUpTo("login") { inclusive = true }
                }
            },
            onDismiss = { display_success_popup = false }
        )
    }

    if (display_error_popup) {
        PopUp(
            icon = R.drawable.xmark_icon,
            message = error_message_popup,
            button_text = "Try again",
            onButtonClick = { display_error_popup = false },
            onDismiss = { display_error_popup = false }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .imePadding(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 700.dp)
                .fillMaxHeight()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 74.dp, bottom = 35.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Albay Reality",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontFamily = TitanOne,
                        fontWeight = FontWeight.Black,
                        color = strokes,
                        drawStyle = Stroke(miter = 10f, width = 12f, join = StrokeJoin.Round)
                    )
                )
                Text(
                    text = "Albay Reality",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontFamily = TitanOne,
                        fontWeight = FontWeight.Black,
                        color = primary
                    )
                )
            }

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
                        Text(
                            text = "Reset Password",
                            color = strokes,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold
                        )

                        val subtitle = when(currentStep) {
                            ResetPasswordStep.ENTER_EMAIL -> "Enter your email to reset password"
                            ResetPasswordStep.SECURITY_QUESTION -> "Security Question: What is your date of birth?"
                        }

                        Text(
                            text = subtitle,
                            color = strokes.copy(alpha = 0.80f),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        when (currentStep) {
                            ResetPasswordStep.ENTER_EMAIL -> {
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
                            }
                            ResetPasswordStep.SECURITY_QUESTION -> {
                                DropdownField(
                                    title = "Birthmonth",
                                    value = birth_month,
                                    options = months,
                                    placeholder = "Select Month",
                                    isError = dob_error,
                                    errorMessage = "Incorrect birth month.",
                                    onValueChange = {
                                        birth_month = it
                                        dob_error = false
                                    }
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                DropdownField(
                                    title = "Birthdate",
                                    value = birth_date,
                                    options = dates,
                                    placeholder = "Select Date",
                                    isError = dob_error,
                                    errorMessage = "Incorrect birth date.",
                                    onValueChange = {
                                        birth_date = it
                                        dob_error = false
                                    }
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                DropdownField(
                                    title = "Birthyear",
                                    value = birth_year,
                                    options = years,
                                    placeholder = "Select Year",
                                    isError = dob_error,
                                    errorMessage = "Incorrect birth year.",
                                    onValueChange = {
                                        birth_year = it
                                        dob_error = false
                                    }
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            text = if (is_loading) "Please wait" else if (currentStep == ResetPasswordStep.SECURITY_QUESTION) "Verify & Reset" else "Next",
                            isPrimary = true,
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                if (!is_connected) {
                                    error_message_popup = "No internet connection."
                                    display_error_popup = true
                                    return@Button
                                }

                                when (currentStep) {
                                    ResetPasswordStep.ENTER_EMAIL -> {
                                        if (email.isBlank()) {
                                            email_error = true
                                            email_error_message = "Email is required."
                                        } else {
                                            is_loading = true
                                            FirebaseFirestore.getInstance().collection("users")
                                                .whereEqualTo("email", email)
                                                .get()
                                                .addOnSuccessListener { documents ->
                                                    is_loading = false
                                                    if (!documents.isEmpty) {
                                                        val doc = documents.documents[0]
                                                        stored_birth_date = doc.getString("birthdate") ?: ""
                                                        user_id = doc.id
                                                        currentStep = ResetPasswordStep.SECURITY_QUESTION
                                                    } else {
                                                        email_error = true
                                                        email_error_message = "Email not found."
                                                    }
                                                }
                                                .addOnFailureListener { e ->
                                                    is_loading = false
                                                    error_message_popup = "Error: ${e.message}"
                                                    display_error_popup = true
                                                }
                                        }
                                    }
                                    ResetPasswordStep.SECURITY_QUESTION -> {
                                        val inputBirthdate = "$birth_year-$birth_month-$birth_date"
                                        if (birth_month.isBlank() || birth_date.isBlank() || birth_year.isBlank()) {
                                            dob_error = true
                                            dob_error_message = "Please complete your birthdate."
                                        } else if (inputBirthdate == stored_birth_date) {
                                            is_loading = true
                                            val authManager = FirebaseAuthManager()
                                            authManager.sendPasswordResetEmail(email, object : FirebaseAuthManager.AuthCallback {
                                                override fun onSuccess() {
                                                    is_loading = false
                                                    display_success_popup = true
                                                }
                                                override fun onFailure(errorMessage: String) {
                                                    is_loading = false
                                                    error_message_popup = errorMessage
                                                    display_error_popup = true
                                                }
                                            })
                                        } else {
                                            dob_error = true
                                            dob_error_message = "Incorrect birthdate. Please try again."
                                        }
                                    }
                                }
                            },
                            is_enabled = !is_loading
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Back to Login",
                            color = primary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .clickable {
                                    navController.navigate("login")
                                }
                        )
                    }
                }
            }
        }
    }
}