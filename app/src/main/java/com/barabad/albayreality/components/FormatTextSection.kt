package com.barabad.albayreality.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barabad.albayreality.ui.theme.Inter

@Composable
fun Heading(text: String) {
    Text(
        text = text,
        fontSize = 22.sp,
        fontFamily = Inter,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun Subheading(text: String) {
    Text(
        text = text,
        fontSize = 18.sp,
        fontFamily = Inter,
        fontWeight = FontWeight.SemiBold,
        color = Color.Black
    )
    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
fun Paragraph(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        fontFamily = Inter,
        color = Color(0xFF444444),
        lineHeight = 20.sp
    )
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun BulletList(items: List<String>) {
    Column {
        items.forEach { item ->
            Row {
                Text("•  ", fontSize = 16.sp)
                Text(
                    text = item,
                    fontSize = 14.sp,
                    fontFamily = Inter,
                    color = Color(0xFF444444),
                    lineHeight = 20.sp,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun NumberList(items: List<String>) {
    Column {
        items.forEachIndexed { index, item ->
            Row {
                Text("${index + 1}.  ", fontSize = 16.sp)
                Text(
                    text = item,
                    fontSize = 14.sp,
                    fontFamily = Inter,
                    color = Color(0xFF444444),
                    lineHeight = 20.sp,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
}
