package com.barabad.albayreality.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barabad.albayreality.R
import com.barabad.albayreality.ui.theme.Inter
import androidx.compose.ui.text.font.FontWeight

@Composable
fun SubHeaderTypeA(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        // # LEFT SECTION
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.mask_icon),
                modifier = Modifier.size(16.dp),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Albay Cultural Heritage",
                fontSize = 12.sp,
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
        }

        Spacer(modifier = Modifier.width(31.dp))

        // # RIGHT SECTION
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Augmented Reality",
                fontSize = 12.sp,
                fontFamily = Inter,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.ar_icon),
                modifier = Modifier.size(16.dp),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
    }
}
