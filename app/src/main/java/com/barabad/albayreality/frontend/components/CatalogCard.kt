package com.barabad.albayreality.frontend.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barabad.albayreality.ui.theme.Inter
import com.barabad.albayreality.ui.theme.primary
import com.barabad.albayreality.ui.theme.strokes

@Composable
fun CatalogCard(
    modifier: Modifier = Modifier,
    title: String,
    button_text: String = "View",
    catalog_image: Int,
    is_enabled: Boolean = true,
    disabled_help_text: String = "View site information first",
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.5.dp, strokes),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.5.dp, strokes.copy(alpha = 0.2f)),
                shadowElevation = 4.dp,
                color = Color.White
            ) {
                Image(
                    painter = painterResource(id = catalog_image),
                    contentDescription = "Site Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // # dynamic title
            Text(
                text = title,
                style = TextStyle(
                    fontFamily = Inter,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = strokes
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // # dynamic button (enabled / disabled state)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (is_enabled) primary else Color.White)
                    .border(
                        BorderStroke(2.dp, strokes),
                        RoundedCornerShape(8.dp)
                    )
                    .clickable(enabled = is_enabled) { onClick() },
                contentAlignment = Alignment.Center
            ) {
                if (is_enabled) {
                    Text(
                        text = button_text,
                        style = TextStyle(
                            fontFamily = Inter,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = strokes
                        )
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Locked",
                        tint = primary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            // # custom help text (only visible when disabled)
            if (!is_enabled) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = disabled_help_text,
                    style = TextStyle(
                        fontFamily = Inter,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = primary
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}