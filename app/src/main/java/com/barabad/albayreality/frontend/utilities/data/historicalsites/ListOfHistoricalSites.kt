package com.barabad.albayreality.frontend.utilities.data.historicalsites

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
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
            map_info = buildAnnotatedString {

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("📍 Location Information\n")
                }

                append(
                    "Camalig is a municipality in Albay that borders Mayon Volcano. It is known for being a great vantage point to view the volcano's majestic shape, and is home to tourist destinations like Sumlang Lake, the Quituinan Hills, and the Hoyop-hoyopan Caves. Camalig has been named by the Provincial Government of Albay as \"The Heritage Town\" for tourism and cultural promotion. Located on B. Buena Street in the heart of the municipality, St. John the Baptist Parish Church is one of the most beautiful historical religious structures in the province.\n\n"
                )

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("👀 What to See\n")
                }

                append(
                    "• ⛪ The Volcanic Stone Façade - The church facade was rebuilt using solid blocks of volcanic rocks from Mayon Volcano herself, with the renovation completed in 1848. The \"1848\" inscription is still visible on the facade to this day.\n" +
                            "• 🔔 The Hexagonal Bell Tower - The 4-storey, hexagonal bell tower on the church's left has massive bells and offers a fine view of Mayon Volcano. During World War II, it served as a lookout for American troops searching for Japanese stragglers hiding in hillside caves.\n" +
                            "• 🎨 The Interior Ceiling Paintings and Heritage Furnishings - The generous donations of the town's affluent residents furnished the church with bells, marble holy water fonts, and even a crystal chandelier. Inside, a ceiling painted with religious images and burial niches add to its grandeur, including a memorial stone honoring a resident who died at the age of 115 years (1797–1912).\n" +
                            "• 🌙 The Church at Night  - The church is stunning at night, glowing under warm amber lights that highlight its volcanic stone texture against the twilight sky.\n\n"
                )

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("🎯 What to Do\n")
                }

                append(
                    "• 📸 Photograph the striking Spanish colonial facade and bell tower, you can also get a glimpse of Mayon Volcano from the right side of the church, especially on clear days.\n" +
                            "• 🏛️ Visit the adjacent convent, which houses a small museum displaying 2,000-year-old bones, beadwork, potsherds, and other artifacts found from Calabidong Cave.\n" +
                            "• 🙏 Attending or observing the Mass, especially during Holy Week,  the church is a popular stop for Visita Iglesia in the province.\n" +
                            "• 🎉 Attend or learn about the Pinangat Festival, held annually from June 10 to 24, coinciding with the town fiesta in honor of St. John the Baptist.\n" +
                            "• 🍲 Try the local specialty: pinangat, Camalig's pride, made from gabi (taro leaves) cooked in coconut milk, best sampled from eateries near the town center.\n\n"
                )

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("💡 Tips for Visitors\n")
                }

                append(
                    "• ☀️ Best time to go: Dry season (November–April) for clearer skies and a better view of Mayon from the church grounds.\n\n" +
                            "• ⏱️ Time your visit: To explore freely and take photos, avoid Sundays and mass hours, weekdays are ideal.\n\n" +
                            "• 👕 What to wear:\n" +
                            " - Modest attire as the church is an active place of worship, avoid shorts or revealing clothing.\n" +
                            " - Comfortable walking shoes; the church grounds and surrounding plaza involve some walking.\n" +
                            " - Light clothing suited to the hot and humid Bicol climate, with a hat and sunscreen.\n" +
                            "• 🎟️ Entrance: It is free to enter and explore Camalig Church and its grounds. Donations are always welcome.\n\n"
                )

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("🚌 How to Commute There\n")
                }

                append(
                    "• 🚙 By Jeepney:\n" +
                            "Route: From Legazpi City, look for a jeepney that plies the Legazpi–Camalig route and tell the driver to drop you near the vicinity of the church. From there you can reach the church on foot.\n" +
                            " 💸 Estimated fare: ₱30–₱40\n" +
                            " ⏱️ Duration: approximately 20–30 minutes\n\n" +
                            "• 🛺 By Tricycle:\n" +
                            "Tricycles are the primary mode of transport within Camalig town and can easily take you to the church and other local attractions. You can negotiate the fare from any point in Camalig.\n\n"
                )

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("🏛️ You Can Also Visit\n")
                }

                append(
                    "🏞️ Hoyop-hoyopan Caves and Sumlang Lake are nearby natural attractions worth combining with your church visit for a fuller Camalig day trip.\n"
                )
            }.toString()
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
            map_info = buildAnnotatedString {

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("📍 Location Information\n")
                }

                append(
                    "Daraga is a first-class municipality in Albay that serves as a key gateway for commerce and tourism thanks to its strategic landlocked location. " +
                            "Its economy is rooted in agriculture, especially rice, coconut, and vegetables. Daraga is known for its scenic and historic attractions. " +
                            "Barangay Busay in Daraga, where the Cagsawa Ruins are specifically located, is a hilly area just north of Legazpi City that serves as the gateway to several attractions in Albay.\n\n"
                )

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("👀 What to See\n")
                }

                append(
                    "• 🌋 View of Mayon Volcano — The ruins offer a dramatic postcard-like frame of Mayon together with the bell tower.\n" +
                            "• ⛪ The Bell Tower — A resilient remnant of the old church symbolizing survival and resilience.\n" +
                            "• 🌿 Lush Gardens — Peaceful gardens surrounding the ruins where visitors can relax and enjoy the scenery.\n\n"
                )

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("🎯 What to Do\n")
                }

                append(
                    "• 📸 Take iconic photographs of the bell tower with Mayon Volcano in the background.\n" +
                            "• 🚶 Explore the ruins and enjoy creative photo opportunities.\n" +
                            "• 🏛️ Visit the Cagsawa National Museum to learn about the site's history.\n" +
                            "• 🛍️ Browse nearby souvenir shops and local handicraft stalls.\n" +
                            "• 🏍️ Experience ATV adventures around Mayon's lava trails.\n" +
                            "• 🌶️ Try local Bicolano cuisine such as Bicol Express and Sili Ice Cream.\n" +
                            "• 🚶 Enjoy the calm atmosphere of the gardens and pathways around the ruins.\n\n"
                )

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("💡 Tips for Visitors\n")
                }

                append(
                    "• ☀️ Best Time to Visit — Dry season from November to April for clearer views of Mayon Volcano.\n" +
                            "• 🌅 Visit early morning or late afternoon to avoid crowds and intense midday heat.\n" +
                            "• 👟 Wear comfortable shoes because the ground may be uneven.\n" +
                            "• 👕 Use light and breathable clothing suitable for the hot climate.\n" +
                            "• 🧴 Bring sunscreen, sunglasses, and a hat for protection from the sun.\n" +
                            "• 📷 Do not forget to take the iconic photo of the majestic Mayon Volcano.\n\n"
                )

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("🚌 How to Commute There\n")
                }

                append(
                    "• 🚙 By Jeepney:\n" +
                            "From Legazpi Grand Terminal, ride a jeepney bound for Daraga or Camalig. Ask to be dropped off at the Cagsawa Ruins.\n" +
                            "   💸 Estimated Fare: ₱50–₱70\n" +
                            "   ⏱️ Estimated Travel Time: 30 minutes to 1 hour\n\n" +

                            "• 🛺 By Tricycle:\n" +
                            "For a more direct ride, you may hire a tricycle from different parts of Legazpi City and negotiate the fare with the driver.\n\n"
                )

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("🏛️ Nearby Attraction\n")
                }

                append(
                    "📚 Bicol National Museum — Located beside the Cagsawa Ruins, the museum showcases the history, culture, and resilience of the Bicol Region and its people throughout Mayon's eruptions."
                )
            }.toString()
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
            map_info = buildAnnotatedString {

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("📍 Location Information\n")
                }

                append(
                    "Legazpi is the capital city of Albay and it serves as Bicol’s hub for transportation, education, health services, commerce, and tourism. \n\n" +
                            "Old Albay District is the historic core of Legazpi City in Abay. This district is rich in colonial-era architecture, plazas and buildings that shows its importance in the city’s history, both political and religious.\n\n"
                )

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("👀 What to see\n")
                }

                append(
                    "• ⛪ St. Gregory the Great Cathedral (Albay Cathedral) - it is an important spiritual institution and a prominent landmark in Old Albay.\n" +
                            "• 🌳 Penaranda Park (Old Albay’s town plaza) as of November 22, 2025 temporarily closed - where the city primarily hosts events, fiestas, and celebrations and is the heart of festive activities in Old Albay \n" +
                            "• 🏛️ Museo de Legazpi - stands as the cultural heart of the city, showcasing the region’s history and cultural heritage.\n" +
                            "• 🏢 Legazpi City Hall - serves as the city’s administrative center and provides essential services for the public.\n\n"
                )

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("🎯 What to do\n")
                }

                append(
                    "• 🏛️ Visit Museo de Legazpi and explore Albay's history and culture through their artifacts and artworks.\n" +
                            "• 🚶‍♂️ Take a walk and relax at Peñaranda park (temporarily closed).\n" +
                            "• 🏘️ Explore the heritage streets around the district to admire the colonial-era buildings.\n" +
                            "• 🌋 Admire the beautiful view of the Mayon Volcano and take a picture during sunset.\n\n"
                )

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("💡 Tips for visitors\n")
                }

                append(
                    "• ☀️ Best time to go: Visit between March to April for Holy Week, May to August for the summer, and November to January for the holidays. August is also the month of the Ibalong festival.\n" +
                            "• ⏱️ Time your visit: Early morning or late afternoon for cooler weather and better lighting for photos.\n" +
                            "• ☂️ Bring sun protection like a hat, umbrella and sunscreen to combat harsh hot weather, and stay hydrated.\n" +
                            "• ⚠️ Keep in mind Peñaranda Park is currently closed until further notice.\n\n"
                )

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("🚌 How to commute there\n")
                }

                append(
                    "• 🚙 By Jeepney:\n" +
                            "Route: From Legazpi Grand Terminal, take a jeepney bound Legazpi, can be Diretso or Alternate routes (depending on route). Ask to be dropped off at the Legazpi City Hall, or Old Albay.\n" +
                            "Frequency of rides to destination: Very frequent\n" +
                            " 💸 Fare: ₱10 - ₱20 \n" +
                            " ⏱️ Duration: 10-15 minutes\n\n" +
                            "• 🛺 By tricycle\n" +
                            "Tricycles are available throughout Legazpi City. \n" +
                            "Good option if you prefer a private ride to Old Albay.\n" +
                            " 💸 Fare: ₱20 - ₱60\n" +
                            " ⏱️ Duration (Within Legazpi City proper): 5-10 minutes\n" +
                            " ⏱️ Duration (From nearby barangays): 10-15 minutes\n\n"
                )

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("🏛️ You can also visit:\n")
                }

                append(
                    "🏟️ Albay Astrodome - open area stadium for sports, leisure and events.\n"
                )
            }.toString()
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
            latitude = 13.14125,
            longitude = 123.74073,
            map_info = buildAnnotatedString {

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("📍 Location Information\n")
                }

                append(
                    "Old Albay is a historic district at the heart of Legazpi City, Bicol, serving as the civic and cultural center of the province of Albay. The district features well-preserved colonial architecture and scenic landscapes, offering a unique blend of history, culture, and natural beauty. Developed based on Spanish style, it is home to a wide variety of establishments, including administrative institutions, religious landmarks, and dining options. At its center sits Peñaranda Park, flanked by the iconic St. Gregory the Great Cathedral to the west.\n\n"
                )

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("👀 What to See\n")
                }

                append(
                    "• 🌋 View of Mayon Volcano - On clear days, both the park and the Cathedral grounds offer breathtaking views of Mayon Volcano, known for its perfect cone shape.\n" +
                            "• ⛪ St. Gregory the Great Cathedral (Catedral de San Gregorio Magno) - One of the oldest Roman Catholic Churches in the Philippines and one of the most prominent landmarks in the Old Albay District. The cathedral features a stunning neo-Gothic design, characterized by soaring spires, intricate stained glass windows, and a serene ambiance.\n" +
                            "• 🌳 Peñaranda Park (Freedom Park) - A lively meeting place where history, culture, and nature blend seamlessly, named in honor of Jose Ma. Peñaranda, Albay's pioneering governor.\n" +
                            "• 🏛️ Historical Monuments - The park features historical showcases such as the Liberty Bell and a small Spanish cannon, along with the Jose Peñaranda statue. The Liberty Bell, installed by American troops in 1945, symbolizes liberation from Japanese occupation.\n\n"
                )

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("🎯 What to Do\n")
                }

                append(
                    "• 🚶 Explore the surrounding Old Albay District for a complete cultural experience, and engage with local guides for deeper insights into the Cathedral's history and significance.\n" +
                            "• 📸 Capture the beauty of the park's historical monuments and the architectural elegance of the surrounding buildings through photography.\n" +
                            "• 🎉 Attend events at the park, which serves as a venue for a variety of gatherings ranging from cultural festivals (such as the annual Magayon Festival held in May) to civic events.\n" +
                            "• 🗺️ Explore nearby landmarks such as the Legazpi City Museum, and browse the vibrant city center.\n" +
                            "• 🍽️ Enjoy a taste of Albay's culinary delights along the charming streets lined with local shops and eateries surrounding the Cathedral.\n" +
                            "• 🧺 Have a picnic or simply relax — the park serves as a gathering place for locals and visitors alike, perfect for families, couples, and solo travelers.\n\n"
                )

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("💡 Tips for Visitors\n")
                }

                append(
                    "• ☀️ Best time to go: Dry season (November - April) for clearer views of Mayon Volcano\n" +
                            "• ⏱️ Time your visit: Early morning or late afternoon is ideal for cooler temperatures and softer light for photography\n\n" +
                            "• 👕 What to wear:\n" +
                            "Comfortable walking shoes, as the area is best explored on foot\n" +
                            "Light and comfortable clothing due to the hot and humid Philippine climate\n" +
                            "Bring a hat and sunglasses, and don't forget sunscreen\n" +
                            "• 📅 Check the schedule for Mass times if you'd like to experience the Cathedral in its vibrant community atmosphere\n" +
                            "• 🌋 Don't forget to look out for Mayon Volcano on clear days, visible from both the park and the Cathedral grounds\n\n"
                )

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("🚌 How to Commute There\n")
                }

                append(
                    "• 🚙 By Jeepney:\n" +
                            "Route: Look for a jeepney with a sign indicating \"Albay\" or \"Daraga,\" which can be caught from various points in Legazpi City.\n" +
                            " 💸 Fare: around ₱13-₱15\n" +
                            " ⏱️ Duration: approximately 10-20 minutes depending on your starting point\n\n" +
                            "• 🚕 By Taxi/Grab:\n" +
                            "The Cathedral is about 7 kilometers from Bicol International Airport, approximately a 15 -minute car ride.\n" +
                            " 💸 Fare: Could vary between ₱300-₱400\n" +
                            " ⏱️ Simply tell the driver \"Peñaranda Park\" or \"Catedral de San Gregorio Magno\", both are well-known landmarks.\n\n"
                )

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("🏛️ You Can Also Visit\n")
                }

                append(
                    "• 🌳 Peñaranda Park - A charming public plaza dedicated to Jose Ma. Peñaranda, the first Governor of Albay. Stroll through its gardens, view the historic Liberty Bell and Spanish cannon, and enjoy a clear-day view of Mayon Volcano. A great spot to relax, people-watch.\n" +
                            "• ⛪ St. Gregory the Great Cathedral (Catedral de San Gregorio Magno) - One of the oldest Roman Catholic Churches in the Philippines, this neo-Gothic landmark features soaring spires, intricate stained glass windows, and a beautifully crafted altar. A must-visit for history buffs, architecture lovers, and the faithful alike.\n" +
                            "• 🛍️ Gregorian Mall - A casual commercial strip located right beside the Cathedral in the Old Albay District. Not quite a mall, but a handy spot with a mix of fast food, cafes, drugstores, and convenience stores, perfect for grabbing a quick bite or picking up essentials after exploring the area. \n" +
                            "• 🏛️ Museo de Legazpi - Accessible from the park's central location, where the past comes alive through captivating exhibits and local heritage."
                )
            }.toString()
        )
    )
}