package com.barabad.albayreality.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import com.barabad.albayreality.ui.theme.Inter
import com.barabad.albayreality.ui.theme.white
import com.barabad.albayreality.ui.theme.white100

@Composable
fun ButtonTypeA(
    imageRes: Int,              // # bg img resource id
    title: String,              // # main tex
    subtitle: String,           // # subtext
    onClick: () -> Unit         // # the function that will be execute when this button is clicked
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 22.dp)
    ) {

        // # If screen is > 500.dp (it is on large screen), scale the height using 40% of the screen width. Else, default to 200.dp
        val cardHeight = if (maxWidth > 500.dp) maxWidth * 0.40f else 200.dp

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(cardHeight)
                .clickable { onClick() },
            shape = RoundedCornerShape(12.dp),
            shadowElevation = 4.dp,
        ) {
            Box {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Text(
                        text = title,
                        fontSize = 18.sp,
                        fontFamily = Inter,
                        fontWeight = FontWeight.Bold,
                        color = white
                    )
                    Text(
                        text = subtitle,
                        fontSize = 12.sp,
                        fontFamily = Inter,
                        fontWeight = FontWeight.Medium,
                        color = white100
                    )
                }
            }
        }
    }
}
