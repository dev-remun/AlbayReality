package com.barabad.albayreality.frontend.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barabad.albayreality.R
import com.barabad.albayreality.features.ImageCarousel
import com.barabad.albayreality.frontend.components.Button
import com.barabad.albayreality.frontend.components.NavBar
import com.barabad.albayreality.ui.theme.Inter
import com.barabad.albayreality.ui.theme.TitanOne
import com.barabad.albayreality.ui.theme.primary
import com.barabad.albayreality.ui.theme.strokes
import kotlinx.coroutines.delay

@Composable
fun ARViewCataglogContentScreen(
    navController: NavController,
    site_title: String,
    site_location: String,
    site_description: String,
    site_images: List<Int>
) {

    var active_tab by remember { mutableStateOf(-1) }

    Scaffold(
        bottomBar = {
            NavBar(
                active_tab = active_tab,
                on_tab_selected = { selected_index ->
                    active_tab = selected_index
                },
                nav_controller = navController
            )
        }
    ) { inner_padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(inner_padding)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 40.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.back_icon),
                        contentDescription = "Back",
                        tint = strokes,
                        modifier = Modifier.size(26.dp)
                    )
                }

                // # ung if-else statement na to ginawa ko para ma-catch ko ung St. John the Baptist Church so pwede ko pa maayos ung orientation ng title niya :<
                // # Medyo mahaba kasi ung title niya kapag one line lang. Kaya nilagyan ko ng '\n'
                // # ung else statement jan na ung other sites
                if (site_title == "St. John the Baptist Church") {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "St. John the\nBaptist Church",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 32.sp,
                                fontFamily = TitanOne,
                                fontWeight = FontWeight.Black,
                                color = strokes,
                                drawStyle = Stroke(miter = 10f, width = 12f, join = StrokeJoin.Round)
                            )
                        )
                        Text(
                            text = "St. John the\nBaptist Church",
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 32.sp,
                                fontFamily = TitanOne,
                                fontWeight = FontWeight.Black,
                                color = primary
                            )
                        )
                    }
                } else {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = site_title,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 32.sp,
                                fontFamily = TitanOne,
                                fontWeight = FontWeight.Black,
                                color = strokes,
                                drawStyle = Stroke(miter = 10f, width = 12f, join = StrokeJoin.Round)
                            )
                        )
                        Text(
                            text = site_title,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 32.sp,
                                fontFamily = TitanOne,
                                fontWeight = FontWeight.Black,
                                color = primary
                            )
                        )
                    }
                }


            }

            // # main content area
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {

                ImageCarousel(images = site_images)

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = site_location,
                    style = TextStyle(
                        fontFamily = Inter,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = strokes
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = site_description,
                    textAlign = TextAlign.Justify,
                    style = TextStyle(
                        fontFamily = Inter,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp,
                        color = strokes,
                        lineHeight = 20.sp
                    )
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    text = "AR Mode",
                    isPrimary = true,
                    onClick = { /* TODO: Navigate to AR Mode */ },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    text = "Quiz Mode",
                    isPrimary = false,
                    onClick = { navController.navigate("games") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

