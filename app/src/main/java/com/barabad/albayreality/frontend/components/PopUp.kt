package com.barabad.albayreality.frontend.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.barabad.albayreality.ui.theme.primary
import com.barabad.albayreality.ui.theme.strokes

@Composable
fun PopUp(
    icon: Int,
    message: String,
    button_text: String,
    onButtonClick: () -> Unit,
    onDismiss: () -> Unit
) {

    Dialog(onDismissRequest = { onDismiss() }) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 3.dp,
                    color = strokes,
                    shape = RoundedCornerShape(16.dp)
                )
                .background(Color.White, RoundedCornerShape(16.dp))
        ) {

            // # Top section (orange background)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = primary,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
                    .padding(vertical = 28.dp),
                contentAlignment = Alignment.Center
            ) {
                // # White circular icon container
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .background(Color.White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = "Popup Icon",
                        tint = strokes,
                        modifier = Modifier.size(42.dp)
                    )
                }
            }

            // # Bottom content
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // # Message
                Text(
                    text = message,
                    color = strokes,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // # Action Button
                Button(
                    text = button_text,
                    isPrimary = true,
                    onClick = onButtonClick,
                    modifier = Modifier.fillMaxWidth() // override width if needed
                )
            }
        }
    }
}