package com.barabad.albayreality.frontend.utilities.data.historicalsites

import com.barabad.albayreality.R
import com.barabad.albayreality.frontend.utilities.data.user_info.UserState

/**
 * is_viewed = isSiteViewed(userID, site)
 * return is_viewed
 *
 */

fun getListOfHistoricalSites(user_state: UserState): List<HistoricalSiteModel> {

    return listOf(
        HistoricalSiteModel(
            site_id = "st_john_church",
            title = "St. John the Baptist Church",
            location = "Camalig, Albay",
            description = "" +
                    "St. John the Baptist Church, also known as Camalig Church, is a historic Roman Catholic parish in Camalig, Albay. It was first built in 1579 by Franciscan missionaries as a simple wooden structure. In 1605, it was reconstructed in stone, marking its development into a more permanent church building. The structure was later destroyed during the 1814 eruption of Mount Mayon, which caused significant disruption in the town and forced residents to relocate temporarily.\n" +
                    "\nWhen the community returned, the church was rebuilt starting in the 1830s using volcanic stones from Mount Mayon. Reconstruction continued over several years and was completed in 1848. Over time, the church received donations from wealthy local families, including church bells, marble holy water fonts, and a crystal chandelier, which contributed to its interior features.\n" +
                    "\nDespite experiencing wars, natural disasters, and repeated volcanic activity, the church has remained standing and continues to serve as a significant landmark in Camalig. Today, it is officially recognized as a Level II Historic Structure by the National Historical Commission of the Philippines and is designated as an Important Cultural Property by the National Museum. It remains an important cultural and historical site in the town.\n",
            images = listOf(R.drawable.churchext, R.drawable.churchint),
            latitude = 13.1820646,
            longitude = 123.6546855,
        ),
        HistoricalSiteModel(
            site_id = "cagsawa_church",
            title = "Cagsawa Ruins Church",
            location = "Busay, Daraga, Albay",
            description = "" +
                    "The Cagsawa Ruins stand as the remains of a stone church complex established in the 18th century by Franciscan missionaries during the Spanish colonial period, at a time when the surrounding settlement of Cagsawa had grown into a relatively prosperous agricultural town. Its location near Mayon Volcano offered both opportunity and risk; the volcano’s eruptions over time enriched the soil with minerals, making the land highly suitable for farming, which encouraged residents to remain despite the known volcanic activity. This long-term trade-off between environmental danger and economic benefit ultimately culminated in the devastating eruption of 1814, widely regarded as one of the most destructive in Mayon’s recorded history. Rather than slow-moving lava flows, it was primarily pyroclastic flows and it has caused widespread destruction and a high number of casualties.\n" +
                    "\nHistorical accounts suggest that many residents sought refuge inside the church, trusting its thick stone walls to provide protection. But the force of the heat of the eruption buried the building along with those inside. Over time, much of the church structure was covered in layers of ash and debris, leaving only portions visible today. Most notably the bell tower, which remains largely due to its height and more exposed position compared to the rest of the structure. \n" +
                    "\nGeologically, Mayon is classified as a stratovolcano, a type formed along tectonic plate boundaries and characterized by periodic explosive eruptions, which helps explain both the fertility of the surrounding land and the recurring hazard it presents. In the present day, the site has been preserved as a cultural and historical landmark rather than restored, allowing it to function as a physical record of the disaster and its impact on human settlement. \n" +
                    "\nIt is frequently used in educational contexts to demonstrate how natural processes such as volcanic deposition can reshape entire communities, while also serving as a major tourist destination where guided tours, historical interpretation, and photography contribute to sustaining public awareness and collective memory of the event.\n",
            images = listOf(R.drawable.cagsawa1, R.drawable.cagsawa2),
            latitude = 13.16611,
            longitude = 123.70111,
        ),
        HistoricalSiteModel(
            site_id = "old_albay_hall",
            title = "Old Albay Hall",
            location = "Legazpi City, Albay",
            description = "" +
                    "The Munisipyo of Albay originally referred to the Spanish colonial Ayuntamiento de Albay, the municipal government that administered several early settlements in the area, including Legazpi (then often called Albay Viejo), Albay Nuevo, and Daraga. During the late Spanish period, local governance in the Philippines was highly centralized under the colonial state, but towns were gradually organized into formal municipalities through royal decrees. One of the key legal foundations for Albay’s municipal structure was the Spanish Royal Decree of November 12, 1889, which helped formalize administrative boundaries and governance systems in the province. Under this system, officials such as the Alcalde (municipal head), Teniente Alcalde (deputy), Registrador (records keeper), and Síndico (legal representative) were appointed, but they ultimately operated under Spanish colonial authority rather than local electoral control. This early structure laid the groundwork for what would later evolve into the modern municipal government.\n" +
                    "\nAs the American colonial period began after 1898, municipal governance in Albay was reorganized under a more standardized civil government system introduced by the Philippine Commission. The Americans replaced many Spanish-era titles and centralized administrative systems with elected municipal officials and codified local government functions through acts such as the Municipal Code (Act No. 82, 1901). During this transition, the municipality of Albay underwent changes in boundaries and naming conventions, eventually leading to the prominence of Legazpi as the administrative center. The shift reflected both practical governance considerations and broader colonial administrative restructuring across the Bicol region. Over time, the municipality was renamed and reorganized multiple times, reflecting shifting political priorities and the gradual development of local autonomy.\n" +
                    "\nThe culmination of these changes came in the mid-20th century, when Legazpi was officially converted into a city through Republic Act No. 2234, signed in 1959. This law marked a significant milestone, formally recognizing Legazpi as a chartered city with its own expanded administrative powers and responsibilities. The transformation from the Spanish Ayuntamiento de Albay to a modern city government illustrates a long historical evolution shaped by Spanish colonial centralization, American administrative reform, and post-independence state-building. Today, the legacy of the early Munisipyo remains embedded in Legazpi’s civic identity, as many of its administrative foundations trace back to these colonial-era governance structures.\n",
            images = listOf(R.drawable.hall1, R.drawable.hall2),
            latitude = 13.1383411,
            longitude = 123.734589,
        ),
        HistoricalSiteModel(
            site_id = "old_presidencia",
            title = "Legazpi Old Presidencia",
            location = "Old Albay, Legazpi City, Albay",
            description = "" +
                    "The Old Presidencia of Legazpi refers to the former municipal government building established during the Spanish colonial period. In Spanish Philippines, the term Presidencia was commonly used to describe the municipal hall, serving as the administrative center where local officials conducted governance, maintained records, and implemented colonial policies. These buildings were central to the political organization of towns and reflected the broader system of centralized colonial administration." +
                    "\nIn Legazpi (historically associated with Albay Viejo), the Presidencia functioned as the seat of local government under Spanish rule, where officials such as the Gobernadorcillo (municipal head) and other appointed functionaries carried out administrative duties. These officials operated under the authority of the Spanish colonial government, and their responsibilities included tax collection, law enforcement, and coordination with higher provincial authorities.\n" +
                    "\nDuring the American colonial period beginning in 1898, the structure and function of municipal governments were reorganized. Spanish-era Presidencias were either modified or replaced to align with the new civil government system introduced through legislation such as the Municipal Code (Act No. 82, 1901). This transition marked a shift from appointed colonial officials to elected local leaders, reflecting broader administrative reforms implemented across the Philippines.\n" +
                    "\nOver time, as Legazpi developed into a more modern administrative center, eventually becoming a chartered city under Republic Act No. 2234 in 1959, the role of the old Presidencia diminished. Today, references to the Old Presidencia primarily highlight its historical significance as part of the evolution of local governance in Legazpi, representing the transition from Spanish colonial administration to modern municipal and city government systems.\n",
            images = listOf(R.drawable.old_presidencia_new, R.drawable.old_presidencia_prev),
            latitude = 13.14402215,
            longitude = 123.722812561326,
        ),
        /*HistoricalSiteModel(
            site_id = "site_five",
            title = "Lorem Ipsum 5",
            location = "Lorem Ipsum, Albay",
            description = "" +
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                    "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat." +
                    "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur." +
                    "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            images = listOf(R.drawable.placeholder_bgimage, R.drawable.placeholder_bgimage),
            latitude = 13.3585,
            longitude = 123.7320,
        ),

        HistoricalSiteModel(
            site_id = "site_six",
            title = "Lorem Ipsum 6",
            location = "Lorem Ipsum, Albay",
            description = "" +
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                    "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat." +
                    "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur." +
                    "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            images = listOf(R.drawable.placeholder_bgimage, R.drawable.placeholder_bgimage),
            latitude = 13.2384,
            longitude = 123.5358,
        )
            is_viewed = user_state.isLocationSiteViewed("site_six")
        )*/
    )
}

val listOfHistoricalSites = listOf(
    HistoricalSiteModel(
        site_id = "st_john_church",
        title = "St. John the Baptist Church",
        location = "Camalig, Albay",
        description = "" +
                "St. John the Baptist Church, also known as Camalig Church, is a historic Roman Catholic parish in Camalig, Albay. It was first built in 1579 by Franciscan missionaries as a simple wooden structure. In 1605, it was reconstructed in stone, marking its development into a more permanent church building. The structure was later destroyed during the 1814 eruption of Mount Mayon, which caused significant disruption in the town and forced residents to relocate temporarily.\n" +
                "\nWhen the community returned, the church was rebuilt starting in the 1830s using volcanic stones from Mount Mayon. Reconstruction continued over several years and was completed in 1848. Over time, the church received donations from wealthy local families, including church bells, marble holy water fonts, and a crystal chandelier, which contributed to its interior features.\n" +
                "\nDespite experiencing wars, natural disasters, and repeated volcanic activity, the church has remained standing and continues to serve as a significant landmark in Camalig. Today, it is officially recognized as a Level II Historic Structure by the National Historical Commission of the Philippines and is designated as an Important Cultural Property by the National Museum. It remains an important cultural and historical site in the town.\n",
        images = listOf(R.drawable.churchext, R.drawable.churchint),
        latitude = 13.1820646,
        longitude = 123.6546855,
    ),
    HistoricalSiteModel(
        site_id = "cagsawa_church",
        title = "Cagsawa Ruins Church",
        location = "Daraga, Albay",
        description = "" +
                "The Cagsawa Ruins stand as the remains of a stone church complex established in the 18th century by Franciscan missionaries during the Spanish colonial period, at a time when the surrounding settlement of Cagsawa had grown into a relatively prosperous agricultural town. Its location near Mayon Volcano offered both opportunity and risk; the volcano’s eruptions over time enriched the soil with minerals, making the land highly suitable for farming, which encouraged residents to remain despite the known volcanic activity. This long-term trade-off between environmental danger and economic benefit ultimately culminated in the devastating eruption of 1814, widely regarded as one of the most destructive in Mayon’s recorded history. Rather than slow-moving lava flows, it was primarily pyroclastic flows and it has caused widespread destruction and a high number of casualties.\n" +
                "\nHistorical accounts suggest that many residents sought refuge inside the church, trusting its thick stone walls to provide protection. But the force of the heat of the eruption buried the building along with those inside. Over time, much of the church structure was covered in layers of ash and debris, leaving only portions visible today. Most notably the bell tower, which remains largely due to its height and more exposed position compared to the rest of the structure.\n" +
                "\nGeologically, Mayon is classified as a stratovolcano, a type formed along tectonic plate boundaries and characterized by periodic explosive eruptions, which helps explain both the fertility of the surrounding land and the recurring hazard it presents. In the present day, the site has been preserved as a cultural and historical landmark rather than restored, allowing it to function as a physical record of the disaster and its impact on human settlement.\n" +
                "\nIt is frequently used in educational contexts to demonstrate how natural processes such as volcanic deposition can reshape entire communities, while also serving as a major tourist destination where guided tours, historical interpretation, and photography contribute to sustaining public awareness and collective memory of the event.\n",
        images = listOf(R.drawable.cagsawa1, R.drawable.cagsawa2),
        latitude = 13.16611,
        longitude = 123.70111,
    ),
    HistoricalSiteModel(
        site_id = "old_albay_hall",
        title = "Old Albay Hall",
        location = "Legazpi City, Albay",
        description = "" +
                "The Munisipyo of Albay originally referred to the Spanish colonial Ayuntamiento de Albay, the municipal government that administered several early settlements in the area, including Legazpi (then often called Albay Viejo), Albay Nuevo, and Daraga. During the late Spanish period, local governance in the Philippines was highly centralized under the colonial state, but towns were gradually organized into formal municipalities through royal decrees. One of the key legal foundations for Albay’s municipal structure was the Spanish Royal Decree of November 12, 1889, which helped formalize administrative boundaries and governance systems in the province. Under this system, officials such as the Alcalde (municipal head), Teniente Alcalde (deputy), Registrador (records keeper), and Síndico (legal representative) were appointed, but they ultimately operated under Spanish colonial authority rather than local electoral control. This early structure laid the groundwork for what would later evolve into the modern municipal government.\n" +
                "\nAs the American colonial period began after 1898, municipal governance in Albay was reorganized under a more standardized civil government system introduced by the Philippine Commission. The Americans replaced many Spanish-era titles and centralized administrative systems with elected municipal officials and codified local government functions through acts such as the Municipal Code (Act No. 82, 1901). During this transition, the municipality of Albay underwent changes in boundaries and naming conventions, eventually leading to the prominence of Legazpi as the administrative center. The shift reflected both practical governance considerations and broader colonial administrative restructuring across the Bicol region. Over time, the municipality was renamed and reorganized multiple times, reflecting shifting political priorities and the gradual development of local autonomy.\n" +
                "\nThe culmination of these changes came in the mid-20th century, when Legazpi was officially converted into a city through Republic Act No. 2234, signed in 1959. This law marked a significant milestone, formally recognizing Legazpi as a chartered city with its own expanded administrative powers and responsibilities. The transformation from the Spanish Ayuntamiento de Albay to a modern city government illustrates a long historical evolution shaped by Spanish colonial centralization, American administrative reform, and post-independence state-building. Today, the legacy of the early Munisipyo remains embedded in Legazpi’s civic identity, as many of its administrative foundations trace back to these colonial-era governance structures.\n",
        images = listOf(R.drawable.placeholder_bgimage, R.drawable.hall2),
        latitude = 13.1383411,
        longitude = 123.734589,
    ),
    HistoricalSiteModel(
        site_id = "old_presidencia",
        title = "Legazpi Old Presidencia",
        location = "Old Albay, Legazpi City, Albay",
        description = "" +
                "The Old Presidencia of Legazpi refers to the former municipal government building established during the Spanish colonial period. In Spanish Philippines, the term Presidencia was commonly used to describe the municipal hall, serving as the administrative center where local officials conducted governance, maintained records, and implemented colonial policies. These buildings were central to the political organization of towns and reflected the broader system of centralized colonial administration." +
                "\nIn Legazpi (historically associated with Albay Viejo), the Presidencia functioned as the seat of local government under Spanish rule, where officials such as the Gobernadorcillo (municipal head) and other appointed functionaries carried out administrative duties. These officials operated under the authority of the Spanish colonial government, and their responsibilities included tax collection, law enforcement, and coordination with higher provincial authorities.\n" +
                "\nDuring the American colonial period beginning in 1898, the structure and function of municipal governments were reorganized. Spanish-era Presidencias were either modified or replaced to align with the new civil government system introduced through legislation such as the Municipal Code (Act No. 82, 1901). This transition marked a shift from appointed colonial officials to elected local leaders, reflecting broader administrative reforms implemented across the Philippines.\n" +
                "\nOver time, as Legazpi developed into a more modern administrative center, eventually becoming a chartered city under Republic Act No. 2234 in 1959, the role of the old Presidencia diminished. Today, references to the Old Presidencia primarily highlight its historical significance as part of the evolution of local governance in Legazpi, representing the transition from Spanish colonial administration to modern municipal and city government systems.\n",
        images = listOf(R.drawable.old_presidencia_prev, R.drawable.old_presidencia_prev),
        latitude = 13.14402215,
        longitude = 123.722812561326,
    ),
    /*HistoricalSiteModel(
        site_id = "lorem5",
        title = "Lorem Ipsum 5",
        location = "Lorem Ipsum, Albay",
        description = "" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat." +
                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur." +
                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        images = listOf(R.drawable.placeholder_bgimage, R.drawable.placeholder_bgimage),
        latitude = 13.3585,
        longitude = 123.7320,
    ),

    HistoricalSiteModel(
        site_id = "lorem6",
        title = "Lorem Ipsum 6",
        location = "Lorem Ipsum, Albay",
        description = "" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat." +
                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur." +
                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        images = listOf(R.drawable.placeholder_bgimage, R.drawable.placeholder_bgimage),
        latitude = 13.2384,
        longitude = 123.5358,
    ),

    HistoricalSiteModel(
        site_id = "lorem7",
        title = "Lorem Ipsum 7",
        location = "Lorem Ipsum, Albay",
        description = "" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat." +
                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur." +
                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        images = listOf(R.drawable.placeholder_bgimage, R.drawable.placeholder_bgimage),
        latitude = 13.2045,
        longitude = 123.7667,
    ),
    HistoricalSiteModel(
        site_id = "lorem8",
        title = "Lorem Ipsum 8",
        location = "Lorem Ipsum, Albay",
        description = "" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat." +
                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur." +
                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        images = listOf(R.drawable.placeholder_bgimage, R.drawable.placeholder_bgimage),
        latitude = 13.4616,
        longitude = 123.6791,
    ),
    HistoricalSiteModel(
        site_id = "lorem9",
        title = "Lorem Ipsum 9",
        location = "Lorem Ipsum, Albay",
        description = "" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat." +
                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur." +
                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        images = listOf(R.drawable.placeholder_bgimage, R.drawable.placeholder_bgimage),
        latitude = 13.0645,
        longitude = 123.5975,
    ),
    HistoricalSiteModel(
        site_id = "lorem10",
        title = "Lorem Ipsum 10",
        location = "Lorem Ipsum, Albay",
        description = "" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat." +
                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur." +
                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        images = listOf(R.drawable.placeholder_bgimage, R.drawable.placeholder_bgimage),
        latitude = 13.1887,
        longitude = 123.5956,
    ),
        is_viewed = false
    ),*/
)