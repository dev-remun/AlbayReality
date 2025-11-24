package com.barabad.albayreality.features

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barabad.albayreality.ui.theme.Inter
import com.barabad.albayreality.R


@Composable
fun TextActivation(selectedPin: String?) {

    when (selectedPin) {
        "cagsawa" -> {
            Spacer(modifier = Modifier.height(24.dp))

            Text("Cagsawa Church",
                fontSize = 24.sp,
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Daraga, Albay",
                fontSize = 14.sp,
                fontFamily = Inter,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Location Information\n" +
                        "Daraga is a first-class municipality in Albay that serves as a key gateway for commerce and tourism thanks to its strategic landlocked location. Its economy is rooted in agriculture; especially rice, coconut and vegetables. Daraga is known for its scenic and historic attractions. Barangay Busay in Daraga (where the Cagsawa Ruins is specifically located) is a hilly area just north of Legazpi City that serves as the gateway to several attractions in Albay. \n" +
                        "\n" +
                        "What to see\n" +
                        "View of the Mayon Volcano - The ruins offer a dramatic, postcard-like frame of the Mayon along with the bell tower (belfry).\n" +
                        "The bell tower (belfry) - a resilient remnant of the old church, symbolizing resilience and survival.\n" +
                        "Lush gardens surrounding the ruins, offering a tranquil, green spot to relax and enjoy the view. \n" +
                        "\n" +
                        "What to do \n" +
                        "Take the iconic photo of the belfry with Mayon in the background; you can ask the locals for the best angles. \n" +
                        "The ruins and the Mayon Volcano make an incredible photo playground. Guides can help you stage photos where you can create fun illusions. \n" +
                        "Visit the Cagsawa National Museum and uncover the history and fascinating stories of this historical site. \n" +
                        "Nearby stalls around the area offering unique handicrafts and souvenirs that you can browse and purchase. \n" +
                        "Embark on an ATV adventure for the closest possible encounter with Mayon's landscape\n" +
                        "Try the local cuisine such as Bicol Express or the surprisingly delicious Sili ice cream\n" +
                        "Embrace the Serenity of the gardens and path around the ruins.\n" +
                        "\n" +
                        "Tips for vIsitors\n" +
                        "Best time to go: Dry Seasons (November - April) for clearer views of the Mayon\n" +
                        "Time your visit: Early morning or late afternoon avoids crowds and midday heat\n" +
                        "What to wear\n" +
                        "Comfortable shoes because you will be walking on uneven ground\n" +
                        "Light and comfortable clothing due to the hot and humid Philippine climate\n" +
                        "Bring a hat and sunglasses and don't forget sunscreen\n" +
                        "Don't forget to snap the iconic shot of the Majestic Mayon volcano \n" +
                        "\n" +
                        "How to commute there\n" +
                        "By Jeepney: \n" +
                        "Route: From Legazpi Grand Terminal, take a jeepney bound for Daraga or Camalig. Ask to be dropped off at the Cagsawa Ruins; just look for the belfry on the highway.\n" +
                        "Frequent Departures\n" +
                        "₱50 - ₱70 \n" +
                        "Duration: 30 minutes - 1 hr\n" +
                        "By Tricycle:  If you prefer a more direct ride; you can negotiate the fare with the tricycle drivers situated across the city. \n" +
                        "\n" +
                        "You can also visit: Bicol National Museum - Located just next to the Cagsawa Church Ruins and it holds dear the history of the Bicol Region, who have been, for centuries, withstanding the eruptions of the Mayon.",
                fontSize = 14.sp,
                fontFamily = Inter,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF444444)
            )

            Spacer(modifier = Modifier.height(20.dp))

            ImageCarousel(
                images = listOf(
                    R.drawable.cagsawa1,
                    R.drawable.cagsawa2
                )
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

        "cityhall" -> {
            Spacer(modifier = Modifier.height(24.dp))

            Text("Old Albay Munisipyo",
                fontSize = 24.sp,
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Legazpi City, Albay",
                fontSize = 14.sp,
                fontFamily = Inter,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Description",
                fontSize = 14.sp,
                fontFamily = Inter,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF444444)
            )

            Spacer(modifier = Modifier.height(20.dp))

            ImageCarousel(
                images = listOf(
                    R.drawable.hall1,
                    R.drawable.hall2
                )
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

        "church" -> {
            Spacer(modifier = Modifier.height(24.dp))

            Text("St. John the Baptist Church",
                fontSize = 24.sp,
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Camalig, Albay",
                fontSize = 14.sp,
                fontFamily = Inter,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Location Information\n" +
                        "The Municipality of Camalig is located on the southern part of Mayon Volcano, bounded by Daraga to the east, Guinbatan to the west and Jovellar to the south. It is widely known among locals for producing the best Pinangat in the region\n" +
                        "\n" +
                        "The town's major industries include: \n" +
                        "Agriculture\n" +
                        "Handicraft \n" +
                        "Processed food with the famous “Pinangat”\n" +
                        "Cement manufacturing \n" +
                        "Agribusiness \n" +
                        "\n" +
                        "What to See \n" +
                        "A majestic stone facade made from volcanic rocks\n" +
                        "Interior paintings by local artist Frank Borin and Interior Art and Furnishings contributed by the towns prominent families including wood, glass, silver, gold, brass or copper\n" +
                        "Historical tombs from notable locals and spanish period along with paintings. \n" +
                        "A level II Historic structure with a marker from the National Historical Commission of the Philippines and is also declared an important Intellectual Cultural Property by the National Museum in 2017 \n" +
                        "\n" +
                        "What to Do\n" +
                        "Explore the church’s architecture and interior, admiring the art, tombs and historical artifacts\n" +
                        "View, learn and admire the historical paintings to historic tombs and spanish period artworks\n" +
                        "Attend and participate in the mass proceedings to experience the spiritual life of the Camalig community \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Mass Schedules - according to mass-schedules.com\n" +
                        "(last updated: May 5, 2023) \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Sunday, Nov. 23\n" +
                        "6:00 AM - 7:00 AM\n" +
                        "8:00 AM - 9:00 AM: English\n" +
                        "10:00 AM - 11:00 AM\n" +
                        "2:00 PM - 3:00 PM\n" +
                        "4:00 PM - 5:00 PM\n" +
                        "6:00 PM - 7:00 PM\n" +
                        "\n" +
                        "Monday, Nov. 24\n" +
                        "6:00 AM - 7:00 AM\n" +
                        "\n" +
                        "Tuesday, Nov. 25\n" +
                        "6:00 AM - 7:00 AM\n" +
                        "\n" +
                        "Wednesday, Nov. 26\n" +
                        "6:00 AM - 7:00 AM\n" +
                        "5:00 PM - 6:00 PM: Perpetual Novena Mass\n" +
                        "\n" +
                        "Thursday, Nov. 27\n" +
                        "6:00 AM - 7:00 AM\n" +
                        "\n" +
                        "Friday, Nov. 28\n" +
                        "6:00 AM - 7:00 AM\n" +
                        "\n" +
                        "Saturday, Nov. 29\n" +
                        "6:00 AM - 7:00 AM\n" +
                        "5:00 PM - 6:00 PM: Anticipated Mass\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Tips for Visitors \n" +
                        " Ensure to wear modest attire when visiting, especially if you plan to attend a mass. \n" +
                        "Avoid clothing that is overly revealing, torn, or has inappropriate messages\n" +
                        "Fit should not be extremely tight or body-revealing\n" +
                        "Maintain a respectful silence within the church premises to honor its spiritual significance\n" +
                        "Be mindful of any restrictions while capturing the beauty of the church\n" +
                        "Explore the surroundings further\n" +
                        "\n" +
                        "How to commute there\n" +
                        "By Jeepney \n" +
                        "Route: From Legazpi City (The capital city of Albay, Bicol) take a jeepney bound for Camalig\n" +
                        "Frequent departures \n" +
                        "₱50–₱70\n" +
                        "Duration: 1-1.5 hours\n" +
                        "By Bus\n" +
                        "Route: From Legazpi Bus Terminal, take a bus heading to Camalig \n" +
                        "Comfortable for longer rides\n" +
                        "Larger luggage capacity\n" +
                        "Duration: 1-1.5 hours \n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "Other places worth visiting in Camalig\n" +
                        "Hoyop-Hoyopan Cave - The word “hoyop” means blow and Hoyop-Hoyopan cave is a natural cave system with wind tunnels (thus the name “Hoyop-Hoyopan”) that create cool airflow inside. \n" +
                        "Quitinday Green Hills - Often called Camaligs “Chocolate HIlls”. Rolling green hills with viewpoints overlooking Mayon and the unique landscape.\n" +
                        "Quitinday Underground River and Lava Tube - An underground river flowing through ancient lava formations from Mayons eruptions. \n" +
                        "Sumlang Lake - A peaceful lake famous for floating bamboo rafts with a direct view of Mayon. \n",
                fontSize = 14.sp,
                fontFamily = Inter,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF444444)
            )

            Spacer(modifier = Modifier.height(20.dp))

            ImageCarousel(
                images = listOf(
                    R.drawable.churchext,
                    R.drawable.churchint
                )
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
