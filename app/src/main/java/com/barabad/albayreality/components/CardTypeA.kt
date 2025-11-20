package com.barabad.albayreality.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.barabad.albayreality.ui.theme.Inter

@Composable
fun CardTypeA(
    modifier: Modifier = Modifier,
    bardWidth: Int,
    header: String,
    body: String
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5)
        ),
        border = BorderStroke(1.dp, Color(0xFFDDDDDD))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // # Header Text
            Text(
                text = header,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Inter
            )

            // Horizontal Bar (~100px = 100.dp)
            Box(
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 12.dp)
                    .width(bardWidth.dp)
                    .height(2.dp)
                    .background(Color(0xFF50B4E0), shape = RoundedCornerShape(2.dp))
            )

            // Body Text
            Text(
                text = body,
                color = Color.DarkGray,
                fontSize = 13.sp,
                lineHeight = 24.sp,
                fontFamily = Inter
            )
        }
    }
}
