package com.barabad.albayreality.frontend.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import com.barabad.albayreality.R
import com.barabad.albayreality.ui.theme.TitanOne
import com.barabad.albayreality.ui.theme.primary
import com.barabad.albayreality.ui.theme.strokes

@Composable
fun Header(
    modifier: Modifier = Modifier,
    nav_controller: NavController,
    title: String,
    show_logout: Boolean = false,
    onLogoutClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {

        // # back button aligned to the left
        IconButton(
            onClick = { nav_controller.popBackStack() },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back_icon),
                contentDescription = "back",
                tint = strokes,
                modifier = Modifier.size(26.dp)
            )
        }

        // # title centered with stroke effect
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 48.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
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
                text = title,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 32.sp,
                    fontFamily = TitanOne,
                    fontWeight = FontWeight.Black,
                    color = primary
                )
            )
        }

        if (show_logout) {
            IconButton(
                onClick = onLogoutClick,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.logout_icon),
                    contentDescription = "logout",
                    tint = strokes,
                    modifier = Modifier.size(26.dp)
                )
            }
        }
    }
}