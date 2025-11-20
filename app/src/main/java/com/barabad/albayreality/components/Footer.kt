package com.barabad.albayreality.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.barabad.albayreality.R
import com.barabad.albayreality.ui.theme.Inter

@Composable
fun Footer(
    isHomeScreen: Boolean = false,          // # true if we are on Home screen
    isAboutUsScreen: Boolean = false,       // # true if we are on About Us screen
    leftIsHomeButton: Boolean = false,      // # true if left should be Home button instead of Back
    leftIcon: ImageVector,
    leftText: String,
    rightIcon: ImageVector,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit
) {
    val highlightColor = Color(0xFF64B5F6) // Light blue highlight
    val normalColor = Color.Black

    Surface(
        tonalElevation = 8.dp,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp), // Rounded top corners
        color = Color(0xFFFEFEFE) // Pastel white background
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp, vertical = 18.dp), // Taller footer
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // LEFT BUTTON
            Row(
                modifier = Modifier
                    .clickable { onLeftClick() }
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val leftTint =
                    if (isHomeScreen && leftIsHomeButton) highlightColor else normalColor

                Icon(
                    imageVector = leftIcon,
                    contentDescription = null,
                    tint = leftTint
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = leftText,
                    color = leftTint,
                    fontSize = 14.sp,
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // RIGHT BUTTON (About Us)
            Row(
                modifier = Modifier
                    .clickable { onRightClick() }
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val rightTint =
                    if (isAboutUsScreen) highlightColor else normalColor

                Icon(
                    painter = painterResource(id = R.drawable.arlogo),
                    modifier = Modifier.size(32.dp),
                    contentDescription = null,
                    tint = rightTint
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Albay Reality",
                    color = rightTint,
                    fontSize = 14.sp,
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
