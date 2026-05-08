package com.barabad.albayreality.frontend.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.barabad.albayreality.R
import com.barabad.albayreality.backend.FirebaseAuthManager
import com.barabad.albayreality.frontend.components.Header
import com.barabad.albayreality.frontend.components.NavBar
import com.barabad.albayreality.frontend.utilities.data.user_info.UserState
import com.barabad.albayreality.ui.theme.strokes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    nav_controller: NavController,
    user_state: UserState = viewModel()
) {

    // # coroutine scope for the logout process
    val coroutine_scope = rememberCoroutineScope()
    var is_logging_out by remember { mutableStateOf(false) }

    val authLogout = FirebaseAuthManager()

    // # extract current user data from viewmodel
    val user = user_state.user_data

    // # format the name strings
    val firstname = user.firstname.ifEmpty { "User" }
    val lastname = user.lastname.ifEmpty { "Name" }
    val middle_initial = if (user.middlename == "NA") ""  else if (user.middlename.isNotEmpty()) {"${user.middlename.first()}."} else ""

    val full_name = "$firstname $middle_initial $lastname".replace("  ", " ").trim()

    // # generate initials for the avatar
    val first_initial = if (firstname.isNotEmpty()) firstname.first().toString() else ""
    val last_initial = if (lastname.isNotEmpty()) lastname.first().toString() else ""
    val initials = "$first_initial$last_initial".uppercase()

    // # state for bottom navigation bar
    var active_tab by remember { mutableStateOf(1) }

    // # fetch user data if not already loaded
    LaunchedEffect(Unit) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid

        // Only fetch if we have a valid authenticated UID
        if (uid != null) {
            user_state.fetchUserData(uid)

        }
    }

    Scaffold(
        bottomBar = {
            NavBar(
                active_tab = active_tab,
                on_tab_selected = { selected_index ->
                    active_tab = selected_index
                },
                nav_controller = nav_controller
            )
        }
    ) { inner_padding ->

        // # brief loading screen pop up
        if (is_logging_out) {
            Dialog(onDismissRequest = {}) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White,
                    shadowElevation = 8.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            color = strokes,
                            modifier = Modifier.size(24.dp),
                            strokeWidth = 3.dp
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Logging out...",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = strokes
                            )
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(inner_padding)
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Header(
                nav_controller = nav_controller,
                title = "User Profile",
                show_logout = true,
                onBackClick = {
                    nav_controller.popBackStack()
                },
                onLogoutClick = {
        // prevent spam-clicking the logout button
        if (!is_logging_out) {
            is_logging_out = true

            coroutine_scope.launch {
                try {
                    // # sign out from firebase
                    authLogout.logoutUser()
                    // # delay for loading
                    delay(800) 

                } finally {
                    is_logging_out = false

                    nav_controller.navigate("login") {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }

                    coroutine_scope.launch {
                        delay(500)
                        user_state.clearUserData()
                    }
                }
            }
        }
    }
            )

            Spacer(modifier = Modifier.height(48.dp))

            // # circular profile avatar dynamic box
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .border(width = 2.dp, color = strokes, shape = CircleShape)
                    .background(Color.White, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = initials,
                    style = TextStyle(
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = strokes
                    )
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // # full name text
            Text(
                text = full_name,
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = strokes
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // # edit profile button
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    nav_controller.navigate("edit_profile")
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.edit_icon),
                    contentDescription = "edit profile",
                    modifier = Modifier.size(16.dp),
                    tint = strokes
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Edit profile",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = strokes
                    )
                )
            }
        }
    }
}