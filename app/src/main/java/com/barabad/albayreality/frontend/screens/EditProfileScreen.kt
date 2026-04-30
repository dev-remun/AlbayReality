package com.barabad.albayreality.frontend.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.barabad.albayreality.R
import com.barabad.albayreality.frontend.components.Button
import com.barabad.albayreality.frontend.components.DropdownField
import com.barabad.albayreality.frontend.components.Header
import com.barabad.albayreality.frontend.components.InputField
import com.barabad.albayreality.frontend.components.NavBar
import com.barabad.albayreality.frontend.components.PasswordInputField
import com.barabad.albayreality.frontend.components.PopUp
import com.barabad.albayreality.frontend.utilities.data.user_info.UserState
import com.barabad.albayreality.frontend.utilities.utils.loadJsonFile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.barabad.albayreality.frontend.utilities.utils.mapRegProvCity
import com.barabad.albayreality.frontend.utilities.utils.parseCities
import com.barabad.albayreality.frontend.utilities.utils.parseProvinces
import com.barabad.albayreality.frontend.utilities.utils.parseRegions
import com.barabad.albayreality.ui.theme.strokes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun EditProfileScreen(
    nav_controller: NavController,
    user_state: UserState = viewModel()
) {
    // # coroutine scope for simulated network loading delays
    val coroutine_scope = rememberCoroutineScope()

    // # retrieve current user details
    val user = user_state.user_data

    // # form state variables for input fields (initialized empty so placeholders display)
    var input_firstname by remember { mutableStateOf("") }
    var input_middlename by remember { mutableStateOf("") }
    var input_lastname by remember { mutableStateOf("") }
    var input_email by remember { mutableStateOf("") }
    var input_password by remember { mutableStateOf("") }
    var input_confirm_password by remember { mutableStateOf("") }

    // # selected dropdown variables
    var selected_region by remember { mutableStateOf("") }
    var selected_province by remember { mutableStateOf("") }
    var selected_city_municipality by remember { mutableStateOf("") }

    val context = LocalContext.current
    // # load json files as a string
    val region_json = loadJsonFile(context, "locations/refregion.json")
    val province_json = loadJsonFile(context, "locations/refprovince.json")
    val city_json = loadJsonFile(context, "locations/refcitymun.json")

    // # parse data
    val regions = parseRegions(region_json)
    val provinces = parseProvinces(province_json)
    val cities = parseCities(city_json)

    // # build mapping
    val location_data = mapRegProvCity(regions, provinces, cities)

    // # determine active locations to populate dropdown lists (falls back to user's original data if no new selection is made)
    val active_region = selected_region.ifBlank { user.region }
    val active_province = selected_province.ifBlank { user.province }

    // # dynamic lists for options based on the active region/province
    val region_options = location_data.keys.sorted()
    val province_options = location_data[active_region]?.keys?.toList()?.sorted() ?: emptyList()
    val city_options = location_data[active_region]?.get(active_province) ?: emptyList()

    // # ui interaction states
    var active_tab by remember { mutableStateOf(1) }
    var is_loading by remember { mutableStateOf(false) }
    var show_success_dialog by remember { mutableStateOf(false) }
    var show_error_dialog by remember { mutableStateOf(false) }

    // # validation states specific to email and passwords
    var email_error by remember { mutableStateOf(false) }
    var email_error_message by remember { mutableStateOf("") }
    var password_error by remember { mutableStateOf(false) }
    var password_error_message by remember { mutableStateOf("") }
    var passwords_match_error by remember { mutableStateOf(false) }

    // # state variables to detect errors in the location dropdown fields
    var has_region_error by remember { mutableStateOf(false) }
    var has_province_error by remember { mutableStateOf(false) }
    var has_citymun_error by remember { mutableStateOf(false) }

    var region_error_message by remember { mutableStateOf("") }
    var province_error_message by remember { mutableStateOf("") }
    var citymun_error_message by remember { mutableStateOf("") }

    // # update avatar and name dynamically based on input or fallback to original user state
    val display_first = input_firstname.ifBlank { user.firstname }
    val display_last = input_lastname.ifBlank { user.lastname }
    val display_middle = input_middlename.ifBlank { user.middlename }

    val initials = "${display_first.firstOrNull() ?: ""}${display_last.firstOrNull() ?: ""}".uppercase()
    val full_name = "$display_first ${display_middle.firstOrNull()?.plus(".") ?: ""} $display_last".replace("  ", " ").trim()

    Scaffold(
        bottomBar = {
            NavBar(
                active_tab = active_tab,
                on_tab_selected = { selected_index -> active_tab = selected_index },
                nav_controller = nav_controller
            )
        }
    ) { inner_padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(inner_padding)
                .padding(top = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // # header
            Header(
                nav_controller = nav_controller,
                title = "Edit Profile",
                show_logout = false
            )

            Spacer(modifier = Modifier.height(16.dp))

            // # avatar
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .border(width = 2.dp, color = strokes, shape = CircleShape)
                    .background(Color.White, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = initials,
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = strokes
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // # full name display
            Text(
                text = full_name,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = strokes
                )
            )

            Spacer(modifier = Modifier.height(60.dp))

            // # form fields container
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                // # personal information section header
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = null, tint = strokes, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Personal Information", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = strokes)
                }

                Spacer(modifier = Modifier.height(16.dp))

                InputField(
                    title = "First Name",
                    value = input_firstname,
                    placeholder = user.firstname.ifEmpty { "enter first name" },
                    onValueChange = { input_firstname = it }
                )

                InputField(
                    title = "Middle Name",
                    value = input_middlename,
                    placeholder = user.middlename.ifEmpty { "enter middle name" },
                    onValueChange = { input_middlename = it }
                )

                InputField(
                    title = "Last Name",
                    value = input_lastname,
                    placeholder = user.lastname.ifEmpty { "enter last name" },
                    onValueChange = { input_lastname = it }
                )

                DropdownField(
                    title = "Region",
                    value = selected_region,
                    options = region_options,
                    placeholder = user.region.ifEmpty { "select region" },
                    isError = has_region_error,
                    errorMessage = region_error_message,
                    onValueChange = { new_region ->
                        selected_region = new_region
                        selected_province = ""
                        selected_city_municipality = ""
                        if (has_region_error) has_region_error = false
                    }
                )

                DropdownField(
                    title = "Province",
                    value = selected_province,
                    options = province_options,
                    placeholder = user.province.ifEmpty { "select province" },
                    isError = has_province_error,
                    errorMessage = province_error_message,
                    onValueChange = { new_province ->
                        selected_province = new_province
                        selected_city_municipality = ""
                        if (has_province_error) has_province_error = false
                    }
                )

                DropdownField(
                    title = "City / Municipality",
                    value = selected_city_municipality,
                    options = city_options,
                    placeholder = user.city_municipality.ifEmpty { "select city / municipality" },
                    isError = has_citymun_error,
                    errorMessage = citymun_error_message,
                    onValueChange = { new_city ->
                        selected_city_municipality = new_city
                        if (has_citymun_error) has_citymun_error = false
                    }
                )

                // # spacing to match the error height space in inputfield
                Spacer(modifier = Modifier.height(24.dp))

                // # account information section header
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Outlined.Lock, contentDescription = null, tint = strokes, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Account Information", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = strokes)
                }

                Spacer(modifier = Modifier.height(16.dp))

                InputField(
                    title = "Email",
                    value = input_email,
                    placeholder = user.email.ifEmpty { "enter email address" },
                    has_error = email_error,
                    error_message = email_error_message,
                    onValueChange = {
                        input_email = it
                        if (email_error) email_error = false
                    }
                )

                PasswordInputField(
                    title = "Password",
                    value = input_password,
                    placeholder = if (user.password.isNotEmpty()) "*".repeat(user.password.length) else "********",
                    has_error = password_error,
                    error_message = password_error_message,
                    onValueChange = {
                        input_password = it
                        if (password_error) password_error = false
                        if (passwords_match_error) passwords_match_error = input_password != input_confirm_password
                    }
                )

                PasswordInputField(
                    title = "Confirm Password",
                    value = input_confirm_password,
                    placeholder = if (user.password.isNotEmpty()) "*".repeat(user.password.length) else "********",
                    has_error = passwords_match_error,
                    error_message = "Passwords do not match.",
                    onValueChange = {
                        input_confirm_password = it
                        if (passwords_match_error) passwords_match_error = input_password != input_confirm_password
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // # action buttons
                if (is_loading) {
                    Button(
                        text = "Please wait",
                        isPrimary = false,
                        is_enabled = false,
                        onClick = { },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        text = "Cancel Changes",
                        isPrimary = false,
                        is_enabled = false,
                        onClick = { },
                        modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    Button(
                        text = "Save Changes",
                        isPrimary = true,
                        onClick = {
                            var has_validation_error = false

                            // # basic email format validation if the user entered a new email
                            if (input_email.isNotBlank() && !android.util.Patterns.EMAIL_ADDRESS.matcher(input_email).matches()) {
                                email_error = true
                                email_error_message = "Invalid email format."
                                has_validation_error = true
                            }

                            // # password length validation (only if they are attempting to change it)
                            if (input_password.isNotBlank() && input_password.length < 6) {
                                password_error = true
                                password_error_message = "Password should be at least 6 characters."
                                has_validation_error = true
                            }

                            // # password match validation
                            if (input_password != input_confirm_password) {
                                passwords_match_error = true
                                has_validation_error = true
                            }

                            // # if the user updated the region but left province blank
                            if (selected_region.isNotBlank() && selected_province.isBlank()) {
                                has_province_error = true
                                province_error_message = "Please update your province."
                                has_validation_error = true
                            }

                            // # if the user updated region/province but left city blank
                            if ((selected_region.isNotBlank() || selected_province.isNotBlank()) && selected_city_municipality.isBlank()) {
                                has_citymun_error = true
                                citymun_error_message = "Please update your city / municipality."
                                has_validation_error = true
                            }

                            if (!has_validation_error) {
                                is_loading = true
                                coroutine_scope.launch {
                                    delay(1500)
                                    is_loading = false

                                    // # assign new states to user_state, keeping original if input was left blank
                                    user_state.setFirstName(input_firstname.ifBlank { user.firstname })
                                    user_state.setMiddleName(input_middlename.ifBlank { user.middlename })
                                    user_state.setLastName(input_lastname.ifBlank { user.lastname })
                                    user_state.setRegion(selected_region.ifBlank { user.region })
                                    user_state.setProvince(selected_province.ifBlank { user.province })
                                    user_state.setCityMun(selected_city_municipality.ifBlank { user.city_municipality })
                                    user_state.setEmail(input_email.ifBlank { user.email })
                                    user_state.setPassword(input_password.ifBlank { user.password })

                                    println("Firstname: " + user_state.getFirstName())
                                    println("Middlename: " + user_state.getMiddleName())
                                    println("Lastname: " + user_state.getLastName())
                                    println("Region: " + user_state.getRegion())
                                    println("Province: " + user_state.getProvince())
                                    println("City/Mun: " + user_state.getCityMun())
                                    println("Email: " + user_state.getEmail())
                                    println("Password: " + user_state.getPassword())

                                    show_success_dialog = true
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        text = "Cancel Changes",
                        isPrimary = false,
                        onClick = {
                            show_error_dialog = true
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }

    // # success dialog overlay
    if (show_success_dialog) {
        PopUp(
            icon = R.drawable.check_icon,
            message = "Changes Saved",
            button_text = "Continue",
            onButtonClick = {
                show_success_dialog = false
                nav_controller.popBackStack()
            },
            onDismiss = {
                show_success_dialog = false
                nav_controller.popBackStack()
            }
        )
    }

    // # error/cancel dialog overlay
    if (show_error_dialog) {
        PopUp(
            icon = R.drawable.xmark_icon,
            message = "Changes Canceled",
            button_text = "Continue",
            onButtonClick = {
                show_error_dialog = false
                nav_controller.popBackStack()
            },
            onDismiss = {
                show_error_dialog = false
                nav_controller.popBackStack()
            }
        )
    }
}