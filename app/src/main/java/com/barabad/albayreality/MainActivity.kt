package com.barabad.albayreality

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.barabad.albayreality.data.DatabaseProvider
import com.barabad.albayreality.data.ThreeDModel
import com.barabad.albayreality.features.ArFailedScan
import com.barabad.albayreality.features.ArSuccessScan
import com.barabad.albayreality.screens.*
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

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_AlbayReality)
        super.onCreate(savedInstanceState)
        checkSystemSupport(this)

        DatabaseProvider.database.addModel(cagsawa_church)
        DatabaseProvider.database.addModel(old_albay_munisipyo)
        DatabaseProvider.database.addModel(st_john_church)

        setContent {
            MaterialTheme {
                val navController = rememberNavController()

                NavHost(navController, startDestination = "home") {
                    composable("home") { HomeScreen(navController) }
                    composable("ar") { ArScreen(navController) }
                    composable("map") { MapScreen(navController) }
                    composable("aboutus") { AboutUsScreen(navController) }
                    composable("ar_failed_scan") { ArFailedScan(navController) }
                    composable("ar_success_scan") { ArSuccessScan(navController) }
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
