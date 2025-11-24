package com.barabad.albayreality.features

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.barabad.albayreality.components.*
import com.barabad.albayreality.R

@Composable
fun TextActivation(selectedPin: String?) {

    when (selectedPin) {

        "cagsawa" -> {
            Spacer(modifier = Modifier.height(24.dp))
            Heading("Cagsawa Church")
            Subheading("Daraga, Albay")

            Paragraph(
                "Daraga is a first-class municipality that serves as a key gateway for commerce and tourism, " +
                        "thanks to its strategic landlocked location. Its economy is rooted in agriculture; especially rice, " +
                        "coconut and vegetables. Daraga is known for its scenic and historic attractions. Barangay Busay in Daraga "+
                "(where the Cagsawa Ruins is specifically located) is a hilly area just north of Legazpi City that serves as the gateway "+
                "to several attractions in Albay.",
            )

            Subheading("What to See")
            BulletList(
                listOf(
                    "View of the Mayon Volcano - The ruins offer a dramatic, postcard-like frame of the Mayon along "+
                    "with the bell tower (belfry).",
                    "The bell tower (belfry) - a resilient remnant of the  old church, symbolizing resilience and survival.",
                    "Lush gardens surrounding the ruins, offering a tranquil, green spot to relax and enjoy the view."
                )
            )

            Subheading("What to Do")
            BulletList(
                listOf(
                    "Take the iconic photo of the belfry with Mayon in the background; you can ask the locals for the best angles.",
                    "The ruins and the Mayon Volcano make an incredible photo playground. Guides can " +
                    "help you stage photos where you can create fun illusions.",
                    "Visit the Cagsawa National Museum and uncover the history and fascinating stories of this historical site. ",
                    "Nearby stalls around the area offering unique handicrafts and souvenirs that you can browse and purchase.",
                    "Embark on an ATV adventure for the closest possible encounter with Mayon's landscape.",
                    "Try the local cuisine such as Bicol Express or the surprisingly delicious Sili ice cream.",
                    "Embrace the Serenity of the gardens and path around the ruins."
                )
            )

            Subheading("Tips for Visitors")
            BulletList(
                listOf(
                    "Best time to go: Dry Seasons (November – April) for clearer views of Mayon",
                    "Time your visit: Early morning or late afternoon avoids crows and midday heat.",
                    "Wear comfortable shoes for uneven paths, light and comfortable clothing, a hat and sunglasses and don't forget sunscreen.",
                    "Don't forget to snap the iconic shot of the Majestic Mayon Volcano."
                )
            )

            Subheading("How to Commute")
            Paragraph("🚌 By Jeepney (₱50–₱70, 30–60 mins)")
            BulletList(
                listOf(
                    "From Legazpi Grand Terminal, take a jeepney bound for Daraga/Camalig route",
                    "Ask to be dropped off at Cagsawa Ruins "+
                    "; just look for the belfry on the highway."
                )
            )

            Paragraph("\uD83D\uDEFA By Tricycle (direct ride, negotiate the fare with the drivers situated across the city)")

            Subheading("You can also visit:")
            Paragraph("Bicol National Museum - Located just next to the Cagsawa Church Ruins "+
            "and it holds dear the history of the Bicol Region, who have been, for centuries, withstanding "+
            "the eruptions of the Mayon.")

            ImageCarousel(
                images = listOf(
                    R.drawable.cagsawa1,
                    R.drawable.cagsawa2
                )
            )
        }

        "cityhall" -> {
            Spacer(modifier = Modifier.height(24.dp))
            Heading("Old Albay Munisipyo")
            Subheading("Legazpi City, Albay")

            Paragraph("Legazpi is the capital city of Albay and it serves as Bicol’s hub for transportation, education, health services, commerce, and tourism. "+
            "Old Albay District is the historic core of Legazpi City in Abay. This district is rich in colonial-era architecture, plazas and buildings that "+
            "shows its importance in the city’s history, both political and religious.")

            Subheading("What to See")
            BulletList(
                listOf(
                    "St. Gregory the Great Cathedral (Albay Cathedral) - it is an important spiritual institution and a prominent landmark in Old Albay.",
                    "Penaranda Park (Old Albay’s town plaza) as of November 22, 2025 temporarily closed - where the city primarily hosts events, fiestas, and celebrations and is the heart of festive activities in Old Albay.",
                    "Museo de Legazpi - stands as the cultural heart of the city, showcasing the region’s history and cultural heritage.",
                    "Legazpi City Hall - serves as the city’s administrative center and provides essential services for the public."
                )
            )

            Subheading("What to Do")
            BulletList(
                listOf(
                    "Visit Museo de Legazpi and explore Albay's history and culture through their artifacts and artworks.",
                    "Take a walk and relax at Peñaranda park (temporarily closed).",
                    "Explore the heritage streets around the district to admire the colonial-era buildings.",
                    "Admire the beautiful view of the Mayon Volcano and take a picture during sunset."
                )
            )

            Subheading("Tips for Visitors")
            BulletList(
                listOf(
                    "Best time to go: Visit between March to April for Holy Week, May to August for the summer, and "+
                    "November to January for the holidays. August is also the month of the Ibalong festival.",
                    "Time your visit: Early morning or late afternoon for cooler weather and better lighting for photos.",
                    "Bring sun protection like a hat, umbrella and sunscreen to combat harsh hot weather, and stay hydrated.",
                    "Keep in mind Peñaranda Park is currently closed until further notice."
                )
            )

            Subheading("How to Commute")
            Paragraph("🚌 By Jeepney (₱10–₱20, 10-15 mins)")
            BulletList(
                listOf(
                    "From Legazpi Grand Terminal, take a jeepney bound Legazpi, can be Diretso or Alternate routes (depending on route). " +
                            "Ask to be dropped off at the Legazpi City Hall, or Old Albay.",
                    "Frequency of rides to destination: Very frequent"
                )
            )
            Paragraph("\uD83D\uDEFA By Tricycle (₱20–₱60)")
            BulletList(
                listOf(
                    "Tricycles are available throughout Legazpi City.",
                    "Good option if you prefer a private ride to Old Albay.",
                    "Duration (Within Legazpi City proper): 5-10 minutes",
                    "Duration (From nearby barangays): 10-15 minutes"
                )
            )

            Subheading("You can also visit:")
            Paragraph("Albay Astrodome - open area stadium for sports, leisure and events")

            ImageCarousel(
                images = listOf(
                    R.drawable.hall1,
                    R.drawable.hall2
                )
            )
        }

        "church" -> {
            Spacer(modifier = Modifier.height(24.dp))
            Heading("St. John the Baptist Church")
            Subheading("Camalig, Albay")

            Paragraph(
                "The Municipality of Camalig is located on the southern part of mayon Volcano, " +
                        "bounded by Daraga to the east, Guinobatan to the west and Jovellar to the south. " +
                        "It is widely known among locals for producing the best Pinangat in the region."
            )

            Subheading("Town's Major Industries:")
            NumberList(
                listOf(
                    "Agriculture",
                    "Handicraft",
                    "Processed foods (notably Pinangat)",
                    "Cement manufacturing",
                    "Agribusiness"
                )
            )

            Subheading("What to See")
            BulletList(
                listOf(
                    "A majestic stone facade made from volcanic rocks.",
                    "Interior paintings by local artist Frank Borin and Interior Art and Furnishings contributed "+
                    "by the towns prominent families including wood, glass, silver, gold, brass or copper.",
                    "Historical tombs from notable locals and spanish period along with paintings.",
                    "A level II Historic structure with a marker from the National Historical Commission "+
                    "of the Philippines and is also declared as an important intellectual Cultural Property by "+
                    "the National Museum in 2017.",
                )
            )

            Subheading("What to Do")
            BulletList(
                listOf(
                    "Explore church's architecture and interior, admiring the art, tombs and historical artifacts.'",
                    "View, learn and admire the historical paintings to historic tombs and spanish period artworks.",
                    "Attend and participate in the mass proceedings to experience the spiritual life of the Camalig community."
                )
            )

            Subheading("Tips for Visitors")
            BulletList(
                listOf(
                    "Wear modest attire, especially during mass",
                    "Maintain a respectful silence within the church premises to honor its spiritual significance.",
                    "Be mindful of any restrictions while capturing the beauty of the church.",
                    "Explore the surroundings further."
                )
            )

            Subheading("How to Commute")
            Paragraph("🚌 By Jeepney (₱50–₱70, 1–1.5 hrs)")
            Paragraph("🚌 By Bus (1-1.5 hrs, comfortable with larger luggage space)")

            Subheading("You can also visit:")
            NumberList(
                listOf(
                    "Hoyop-Hoyopan Cave – The word \"hoyop\" means blow and Hoyop-Hoyopan cave is a natural cave system with wind tunnels (thus the name \"Hoyop-Hoyopan\") that create cool airflow inside.",
                    "Quitinday Green Hills – Often called Camaligs \"Chocolate Hills\". Rolling green hills with viewpoints overlooking Mayon and the unique landscape.",
                    "Quitinday Underground River and Lava Tube - An underground river flowing through ancient lava formations from Mayons eruptions.",
                    "Sumlang Lake – A peaceful lake famous for floating bamboo rafts and a direct view of Mayon."
                )
            )

            ImageCarousel(
                images = listOf(
                    R.drawable.churchext,
                    R.drawable.churchint
                )
            )
        }
    }
}
