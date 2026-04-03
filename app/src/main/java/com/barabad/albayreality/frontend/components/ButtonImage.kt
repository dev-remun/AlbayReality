package com.barabad.albayreality.frontend.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barabad.albayreality.ui.theme.Inter
import com.barabad.albayreality.ui.theme.strokes

@Composable
fun ButtonImageA(
    image_res: Int,
    title: String,
    on_click: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .clickable { on_click() },
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.dp, strokes),
        color = Color.White,
        shadowElevation = 4.dp
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            // # the image takes up the exact space of the surface
            Image(
                painter = painterResource(id = image_res),
                contentDescription = title,
                contentScale = ContentScale.Crop, // # 'crop' forces the image to fill the entire box
                modifier = Modifier.fillMaxSize()
            )

            // # text positioned at the bottom left
            Text(
                text = title,
                fontSize = 18.sp,
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                color = strokes,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 20.dp, bottom = 16.dp)
            )
        }
    }
}

@Composable
fun ButtonImageB(
    image_res: Int,
    title: String,
    on_click: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .clickable { on_click() },
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.dp, strokes),
        color = Color.White,
        shadowElevation = 4.dp
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = image_res),
                contentDescription = title,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )

            // # text positioned at the center
            Text(
                text = title,
                fontSize = 20.sp,
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                color = strokes,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
            )
        }
    }
}