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
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.barabad.albayreality.data.DatabaseProvider
import com.barabad.albayreality.data.ThreeDModel
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
import com.barabad.albayreality.frontend.utilities.data.user_registration.UserRegistrationInformations
import com.barabad.albayreality.frontend.utilities.data.historicalsites.listOfHistoricalSites
import com.barabad.albayreality.frontend.utilities.data.quizzes.QuizState
import com.barabad.albayreality.frontend.utilities.data.user_info.UserState
import java.util.Objects

class MainActivity : ComponentActivity() {

    val cagsawa_church = ThreeDModel(
        qr_code = "albayrealitycagsawa",
        name = "Cagsawa Church",
        location = "Daraga, Albay",
        description = "The first stone church of Cagsawa was built in 1675 by Fray Acacio de la Concepcion, OFM under the patronage of St. James the Great, and in 1724 it was demolished and rebuilt into a larger church with a masonry convent, belfry, courthouse (casa tribunal) that also served as a primary school, and four masonry storerooms by Fray Francisco Blanco, OFM.\n\nOn February 1, 1814, the eve of the feast of Nuestra Señora de Candelaria, Mayon Volcano—about 10 kilometers from Cagsawa—erupted violently, producing pyroclastic flows, volcanic lightning, ashfall, and lahar that devastated nearby towns in Albay. The eruption buried Cagsawa and killed thousands in Camalig, Cagsawa, Budiao, and Guinobatan, including more than 1,200 people who sought refuge in the church, leaving only the bell tower standing."
    )
    val old_albay_munisipyo = ThreeDModel(
        qr_code = "albayrealitymunisipyo",
        name = "Old Albay Munisipyo",
        location = "Legazpi City, Albay",
        description = "The Munisipyo of Albay originally referred to the Ayuntamiento de Albay, the Spanish-era municipal government that administered the settlements of Legazpi (Albay Viejo), Albay Nuevo, and Daraga. Formalized under the Spanish Royal Decree of November 12, 1889, it established Albay as an organized municipality with appointed local officials such as the Alcalde, Teniente Alcalde, Registrador, and Síndico. The decree later changed the name of the Municipality of Albay to Legazpi, marking a major step in the town’s political identity. \n\nThrough American rule and later independence, the municipality experienced multiple reorganizations—being reverted, merged, and renamed—until Legazpi was finally reinstated as a city in 1959 under Republic Act 2234. The Munisipyo played a central role in shaping Legazpi’s civic structure and remains foundational to the city’s administrative history.")
    val st_john_church = ThreeDModel(
        qr_code = "albayrealitystjohnchurch",
        name = "St. John the Baptist Church",
        location = "Camalig, Albay",
        description = "St. John the Baptist Church, also known as Camalig Church, is a historic Roman Catholic parish in Camalig, Albay. First built in 1579 by Franciscan missionaries as a simple wooden structure, it was later reconstructed in stone in 1605. The church was destroyed during the 1814 eruption of Mount Mayon, which forced the town to relocate temporarily. When residents returned, they rebuilt the church using volcanic stones from Mayon, beginning restoration work in the 1830s and completing it in 1848.\n\nOver time, wealthy local families donated significant furnishings such as bells, marble holy water fonts, and a crystal chandelier, giving the interior a distinctive heritage character. The church has survived wars, natural disasters, and volcanic activity, symbolizing the resilience and faith of the community. Today, it is officially recognized as a Level II Historic Structure by the National Historical Commission and designated as an Important Cultural Property by the National Museum, making it a central cultural landmark in Camalig and a key part of the town’s identity as a heritage area.")

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

        DatabaseProvider.database.addModel(cagsawa_church)
        DatabaseProvider.database.addModel(old_albay_munisipyo)
        DatabaseProvider.database.addModel(st_john_church)

        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                val user_registration_info_object = remember { UserRegistrationInformations() }
                val quiz_state: QuizState = viewModel()
                val user_info_state: UserState = viewModel()

                NavHost(navController, startDestination = "profile") {
                    composable("login") { LogInScreen(navController) }
                    composable("register1") { RegisterScreen1(navController, user_registration_info_object) }
                    composable("register2") { RegisterScreen2(navController, user_registration_info_object) }
                    composable("register3") { RegisterScreen3(navController, user_registration_info_object) }
                    composable("register4") { RegisterScreen4(navController, user_registration_info_object) }
                    composable("register5") { RegisterScreen5(navController, user_registration_info_object) }
                    composable("landing") { LandingScreen(navController) }
                    composable("home") { HomeScreen(navController) }

                    composable("catalogs") { ARCatalogsScreen(navController) }
                    composable("view_catalog/{site_id}") { back_stack_entry ->
                        val site_id = back_stack_entry.arguments?.getString("site_id")

                        val site_data = listOfHistoricalSites.find { it.site_id == site_id }

                        if (site_data != null) {
                            ARViewCataglogContentScreen(
                                navController = navController,
                                site_id = site_data.site_id,
                                site_title = site_data.title,
                                site_location = site_data.location,
                                site_description = site_data.description,
                                site_images = site_data.images
                            )
                        } else {
                            Text("Site not found")
                        }
                    }

                    composable("armode/{site_id}") { back_stack_entry ->
                        val site_id = back_stack_entry.arguments?.getString("site_id")

                        val site_data = listOfHistoricalSites.find { it.site_id == site_id }

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
                        val site_data = listOfHistoricalSites.find { it.site_id == site_id }

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
                        val site_data = listOfHistoricalSites.find { it.site_id == site_id }

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

                    composable("argame_summary/{site_id}") { back_stack_entry ->
                        val site_id = back_stack_entry.arguments?.getString("site_id") ?: ""
                        val site_data = listOfHistoricalSites.find { it.site_id == site_id }

                        if (site_data != null) {
                            ARGameSummaryScreen(
                                navController = navController,
                                site_title = site_data.title,
                                quiz_state = quiz_state
                            )
                        }

                    }

                    composable("edit_profile") { EditProfileScreen(navController, user_info_state) }

                    composable("profile") { ProfileScreen(navController, user_info_state) }
                    composable("games") { ARGameScreen(navController) }
                    composable("map") { ARMapScreen(navController) }
                    composable("aboutus") { AboutUsScreen(navController) }
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
