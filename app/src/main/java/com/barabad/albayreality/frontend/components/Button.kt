package com.barabad.albayreality.frontend.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barabad.albayreality.ui.theme.primary
import com.barabad.albayreality.ui.theme.strokes

@Composable
fun Button(
    text: String,
    isPrimary: Boolean,
    onClick: () -> Unit,
    is_enabled: Boolean = true,
    modifier: Modifier = Modifier,
) {
    // # Determine background color based on the type of button
    val background_color = if (isPrimary) primary else Color.White

    Button(
        onClick = onClick,
        enabled = is_enabled,
        modifier = modifier
            .height(60.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = background_color,
            contentColor = strokes
        ),
        border = BorderStroke(2.dp, strokes)
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}