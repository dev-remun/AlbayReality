package com.barabad.albayreality.frontend.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.barabad.albayreality.ui.theme.primary
import com.barabad.albayreality.ui.theme.strokes
import com.barabad.albayreality.ui.theme.TitanOne
import com.barabad.albayreality.frontend.components.Button
import com.barabad.albayreality.frontend.components.DropdownField
import com.barabad.albayreality.frontend.utilities.data.user_registration.UserRegistrationInformations
import com.barabad.albayreality.frontend.utilities.utils.loadJsonFile
import com.barabad.albayreality.frontend.utilities.utils.mapRegProvCity
import com.barabad.albayreality.frontend.utilities.utils.parseCities
import com.barabad.albayreality.frontend.utilities.utils.parseProvinces
import com.barabad.albayreality.frontend.utilities.utils.parseRegions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen4(navController: NavController, user_registration_info_object: UserRegistrationInformations) {

    val context = LocalContext.current  // # get context inside compose

    // # load json files as a strihng
    val region_json = loadJsonFile(context, "locations/refregion.json")
    val province_json = loadJsonFile(context, "locations/refprovince.json")
    val city_json = loadJsonFile(context, "locations/refcitymun.json")

    // # parse data
    val regions = parseRegions(region_json)
    val provinces = parseProvinces(province_json)
    val cities = parseCities(city_json)

    // # build mapping
    val location_data = mapRegProvCity(regions, provinces, cities)

    // # selected region of the user
    var selected_region by remember { mutableStateOf(user_registration_info_object.user_registration_info.region) }

    // # selected province of the user
    var selected_province by remember { mutableStateOf(user_registration_info_object.user_registration_info.province) }

    // # selected city / municipality of the user
    var selected_city_municipality by remember { mutableStateOf(user_registration_info_object.user_registration_info.city_municipality) }

    // # dynamic lists for options
    val region_options = location_data.keys.sorted()
    val province_options = location_data[selected_region]?.keys?.toList()?.sorted() ?: emptyList()
    val city_options = location_data[selected_region]?.get(selected_province) ?: emptyList()

    // # state variables to detect errors in the input fields
    var has_region_error by remember { mutableStateOf(false) }
    var has_province_error by remember { mutableStateOf(false) }
    var has_citymun_error by remember { mutableStateOf(false) }

    // # state variables custom error message
    var region_error_message by remember { mutableStateOf("") }
    var province_error_message by remember { mutableStateOf("") }
    var citymun_error_message by remember { mutableStateOf("") }

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
            // # outline text
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
            // # fill text
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

        // # register form
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
                    text = "Please select your location",
                    color = strokes.copy(alpha = 0.80f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(32.dp))

                // # region dropdown
                DropdownField(
                    title = "Region",
                    value = selected_region,
                    options = region_options,
                    placeholder = "Select Region",
                    isError = has_region_error,
                    errorMessage = region_error_message,
                    onValueChange = { new_region ->
                        selected_region = new_region
                        selected_province = ""              // # reset province
                        selected_city_municipality = ""     // # reset city
                        if (has_region_error) has_region_error = false
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                // # province dropdown
                DropdownField(
                    title = "Province",
                    value = selected_province,
                    options = province_options,
                    placeholder = "Select Province",
                    isError = has_province_error,
                    errorMessage = province_error_message,
                    onValueChange = { new_province ->
                        selected_province = new_province       // # update selected province
                        selected_city_municipality = ""        // # reset city when province changes
                        if (has_province_error) has_province_error = false
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                // # city / municipality dropdown
                DropdownField(
                    title = "City / Municipality",
                    value = selected_city_municipality,
                    options = city_options,
                    placeholder = "Select City / Municipality",
                    isError = has_citymun_error,
                    errorMessage = citymun_error_message,
                    onValueChange = { new_city ->
                        selected_city_municipality = new_city   // # update selected city / municipality
                        if (has_citymun_error) has_citymun_error = false
                    }
                )

                Spacer(modifier = Modifier.height(40.dp))

                // # next button
                Button(
                    text = "Next",
                    isPrimary = true,
                    onClick = {
                        var has_error = false

                        if (selected_region.isBlank()) {
                            has_region_error = true
                            region_error_message = "Please input your region."
                            has_error = true
                        }
                        if (selected_province.isBlank()) {
                            has_province_error = true
                            province_error_message = "Please input your province."
                            has_error = true
                        }
                        if (selected_city_municipality.isBlank()) {
                            has_citymun_error = true
                            citymun_error_message = "Please input your city / municipality."
                            has_error = true
                        }

                        if (!has_error) {

                            user_registration_info_object.updateUserRegistrationInformation("region", selected_region)
                            user_registration_info_object.updateUserRegistrationInformation("province", selected_province)
                            user_registration_info_object.updateUserRegistrationInformation("city_municipality", selected_city_municipality)

                            // # log selection for debugging
                            Log.d("register_screen4", "Region: $selected_region")
                            Log.d("register_screen4", "Province: $selected_province")
                            Log.d("register_screen4", "City/Muni: $selected_city_municipality")

                            Log.d("register_screen4", "First Name: ${user_registration_info_object.user_registration_info.firstname}")
                            Log.d("register_screen4", "Middle Name: ${user_registration_info_object.user_registration_info.middlename}")
                            Log.d("register_screen4", "Last Name: ${user_registration_info_object.user_registration_info.lastname}")
                            Log.d("register_screen4", "Sex: ${user_registration_info_object.user_registration_info.sex}")
                            Log.d("register_screen4", "Birth Month: ${user_registration_info_object.user_registration_info.birth_month}")
                            Log.d("register_screen4", "Birth Date: ${user_registration_info_object.user_registration_info.birth_date}")
                            Log.d("register_screen4", "Birth Year: ${user_registration_info_object.user_registration_info.birth_year}")
                            Log.d("register_screen4", "Region: ${user_registration_info_object.user_registration_info.region}")
                            Log.d("register_screen4", "Province: ${user_registration_info_object.user_registration_info.province}")
                            Log.d("register_screen4", "City/Muni: ${user_registration_info_object.user_registration_info.city_municipality}")

                            navController.navigate("register5")
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}