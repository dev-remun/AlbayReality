package com.barabad.albayreality.frontend.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.barabad.albayreality.frontend.components.Header

@Composable
fun ARModeScreen(
    navController: NavController,
    site_id: String,
    site_title: String
) {

    Scaffold(
        containerColor = Color.Black
    ) { inner_padding ->

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            // # halo jj, dito na lang ata sa baba neto ung sa pag-display ng AR, glgl.
            // # i-full screen mo na lang ung camera niya. dont worry sa Header() na nasa baba neto kasi floating na yan hahahah
            // # tsaaka gamitin mo na lang ung site_id sa pag-display ng 3d model kasi ung site_id is the same sa file name ng 3d model ni ely.
            // # please refer sa frontend/utilities/data/historicalsites/ListOfHistoricalSites na folder para sa site_id
            // # please refer sa assets/models para sa names ng 3d models ni ely
            // # tyty

            // # Floating Header
            Header(
                nav_controller = navController,
                title = site_title,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = inner_padding.calculateTopPadding() + 16.dp)
            )
        }
    }
}