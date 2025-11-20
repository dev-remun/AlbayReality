package com.barabad.albayreality.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp

import com.barabad.albayreality.R
import com.barabad.albayreality.ui.theme.TitanOne


@Composable
fun Header(
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color.Transparent,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 12.dp, end = 12.dp, bottom = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arlogo),
                contentDescription = null,
                modifier = Modifier.size(54.dp),
                tint = Color.Unspecified,
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "ALBAY REALITY",
                fontFamily = TitanOne,
                fontSize = 28.sp,
                color = Color.Black

            )
        }
    }
}
