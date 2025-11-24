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
                text = "Description",
                fontSize = 14.sp,
                fontFamily = Inter,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF444444)
            )
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
                text = "Description",
                fontSize = 14.sp,
                fontFamily = Inter,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF444444)
            )
        }
    }
}