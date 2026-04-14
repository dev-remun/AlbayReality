package com.barabad.albayreality.frontend.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold // # added scaffold import
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.barabad.albayreality.frontend.components.NavBar // # imported your new navbar
import com.barabad.albayreality.R
import com.barabad.albayreality.frontend.components.ButtonImageA
import com.barabad.albayreality.frontend.components.ButtonImageB
import com.barabad.albayreality.ui.theme.Inter
import com.barabad.albayreality.ui.theme.TitanOne
import com.barabad.albayreality.ui.theme.primary
import com.barabad.albayreality.ui.theme.strokes


@Composable
fun ARGameScreen(navController: NavController) {

    var active_tab by remember { mutableStateOf(-1) }
    // can the value be passed here kung anong site id ang tig press? para ma build a quiz nalang ako on one screen and not multiple
    // also
    // # scaffold handles the layout for top bars, bottom bars, and floating action buttons
    Scaffold(
        bottomBar = {
            NavBar(
                active_tab = active_tab,
                on_tab_selected = { selected_index ->
                    active_tab = selected_index
                    // # 0 - the active is home
                    // # 1 - the active is profile
                    // # 2 - the active is about us
                    // # -1 - there is no active in the nav bar
                },
                nav_controller = navController
            )
        }
    ) { inner_padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(inner_padding)
                .background(Color.White)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.25f),
                contentAlignment = Alignment.Center
            ) {
                // # fill text for app title
                Text(
                    //put this to the top of screen  or something
                    text = "Game Screen",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = TitanOne,
                        fontWeight = FontWeight.Black,
                        color = primary
                    )
                )
                //not yet implemented so commented out muna
                //Quiz(site_id)
            }

        }
    }
}


@Composable
fun Quiz(quizNo: String?) {
    TODO("Not yet implemented")
}
