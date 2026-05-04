package com.barabad.albayreality

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.*
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.barabad.albayreality.frontend.screens.ARCatalogsScreen
import com.barabad.albayreality.frontend.screens.ARGamePlaygroundScreen
import com.barabad.albayreality.frontend.screens.ARGameResultScreen
import com.barabad.albayreality.frontend.screens.ARGameScreen
import com.barabad.albayreality.frontend.screens.ARGameSummaryScreen
import com.barabad.albayreality.frontend.screens.ARMapScreen
import com.barabad.albayreality.frontend.screens.ARModeScreen
import com.barabad.albayreality.frontend.screens.ARViewCataglogContentScreen
import com.barabad.albayreality.frontend.screens.AboutUsScreen
import com.barabad.albayreality.frontend.screens.EditProfileScreen
import com.barabad.albayreality.frontend.screens.HomeScreen
import com.barabad.albayreality.frontend.screens.LandingScreen
import com.barabad.albayreality.frontend.screens.LogInScreen
import com.barabad.albayreality.frontend.screens.ProfileScreen
import com.barabad.albayreality.frontend.screens.RegisterScreen1
import com.barabad.albayreality.frontend.screens.RegisterScreen2
import com.barabad.albayreality.frontend.screens.RegisterScreen3
import com.barabad.albayreality.frontend.screens.RegisterScreen4
import com.barabad.albayreality.frontend.screens.RegisterScreen5
import com.barabad.albayreality.frontend.utilities.data.historicalsites.getListOfHistoricalSites
import com.barabad.albayreality.frontend.utilities.data.user_registration.UserRegistrationInformations
import com.barabad.albayreality.frontend.utilities.data.quizzes.QuizState
import com.barabad.albayreality.frontend.utilities.data.user_info.UserState
import com.google.firebase.auth.FirebaseAuth
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.Objects

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_AlbayReality)
        super.onCreate(savedInstanceState)
        checkSystemSupport(this)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        val controller = WindowInsetsControllerCompat(window, window.decorView)

        // # this hides the status and navigation bar of the phone
        controller.hide(WindowInsetsCompat.Type.systemBars())

        // # this shows the nav and status bar, if the user swipe the nav or status bar
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        setContent {
            MaterialTheme {
                val current_user = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser
                val start_screen = if (current_user != null) "home" else "landing"

                val navController = rememberNavController()
                val user_registration_info_object = remember { UserRegistrationInformations() }
                val quiz_state: QuizState = viewModel()
                val user_info_state: UserState = viewModel()

                // Auto Log-in skips landing and login page
                val startDestination = if (FirebaseAuth.getInstance().currentUser != null) {
                    "home"
                } else {
                    "landing"
                }

                NavHost(navController = navController, startDestination = startDestination) {
                    composable("login") { LogInScreen(navController, user_info_state) }
                    composable("register1") { RegisterScreen1(navController, user_registration_info_object) }
                    composable("register2") { RegisterScreen2(navController, user_registration_info_object) }
                    composable("register3") { RegisterScreen3(navController, user_registration_info_object) }
                    composable("register4") { RegisterScreen4(navController, user_registration_info_object) }
                    composable("register5") { RegisterScreen5(navController, user_registration_info_object) }
                    composable("landing") { LandingScreen(navController) }
                    composable("home") { HomeScreen(navController) }
                    composable("games") { ARGameScreen(navController, user_info_state) }
                    composable("profile") { ProfileScreen(nav_controller = navController, user_state = user_info_state) }
                    composable("aboutus") { AboutUsScreen(navController) }
                    composable("catalogs") { ARCatalogsScreen(navController, user_info_state) }
                    composable("view_catalog/{site_id}") { back_stack_entry ->
                        val site_id = back_stack_entry.arguments?.getString("site_id")

                        val site_data = getListOfHistoricalSites(user_info_state).find { it.site_id == site_id }

                        if (site_data != null) {
                            ARViewCataglogContentScreen(
                                navController = navController,
                                site_id = site_data.site_id,
                                site_title = site_data.title,
                                site_location = site_data.location,
                                site_description = site_data.description,
                                site_images = site_data.images,
                                user_state = user_info_state
                            )
                        } else {
                            Text("Site not found")
                        }
                    }

                    composable("armode/{site_id}") { back_stack_entry ->
                        val site_id = back_stack_entry.arguments?.getString("site_id")

                        val site_data = getListOfHistoricalSites(user_info_state).find { it.site_id == site_id }

                        if (site_data != null) {
                            ARModeScreen(
                                navController = navController,
                                site_id = site_data.site_id,
                                site_title = site_data.title
                            )
                        } else {
                            Text("Site not found")
                        }
                    }

                    composable("argame_playground/{site_id}") { back_stack_entry ->
                        val site_id = back_stack_entry.arguments?.getString("site_id")
                        val site_data = getListOfHistoricalSites(user_info_state).find { it.site_id == site_id }

                        if (site_data != null) {
                            ARGamePlaygroundScreen(
                                navController = navController,
                                site_id = site_data.site_id,
                                site_title = site_data.title,
                                quiz_state = quiz_state
                            )
                        }
                    }

                    composable("argame_result/{site_id}/{result_status}") { back_stack_entry ->
                        val site_id = back_stack_entry.arguments?.getString("site_id") ?: ""
                        val result_status = back_stack_entry.arguments?.getString("result_status") ?: ""
                        val site_data = getListOfHistoricalSites(user_info_state).find { it.site_id == site_id }

                        if (site_data != null) {
                            ARGameResultScreen(
                                navController = navController,
                                site_title = site_data.title,
                                site_id = site_id,
                                result_status = result_status,
                                quiz_state = quiz_state
                            )
                        }
                    }

                    composable("argame_summary/{site_id}/{site_title}") { backStackEntry ->
                        val siteId = backStackEntry.arguments?.getString("site_id") ?: ""
                        val siteTitle = backStackEntry.arguments?.getString("site_title") ?: ""

                        ARGameSummaryScreen(
                            navController = navController,
                            site_id = siteId,
                            site_title = siteTitle,
                            quiz_state = quiz_state
                        )
                    }
                }
            }
        }
    }
}

fun checkSystemSupport(activity: Activity): Boolean {
    // checking whether the API version of the running Android >= 24
    // that means Android Nougat 7.0

    val openGlVersion =
        (Objects.requireNonNull<Any?>(activity.getSystemService(Context.ACTIVITY_SERVICE)) as ActivityManager).deviceConfigurationInfo
            .getGlEsVersion()

    // checking whether the OpenGL version >= 3.0
    if (openGlVersion.toDouble() >= 3.0) {
        return true
    } else {
        Toast.makeText(activity, "App needs OpenGl Version 3.0 or later", Toast.LENGTH_LONG)
            .show()
        activity.finish()
        return false
    }
}
