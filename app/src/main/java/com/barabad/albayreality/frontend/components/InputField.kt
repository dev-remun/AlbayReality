package com.barabad.albayreality.frontend.components

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barabad.albayreality.ui.theme.hint_color
import com.barabad.albayreality.ui.theme.inputfield_bg
import com.barabad.albayreality.ui.theme.primary
import com.barabad.albayreality.ui.theme.strokes
import com.barabad.albayreality.ui.theme.error_message_color

@Composable
fun InputField(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    has_error: Boolean = false,
    error_message: String = "",
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val errorTextHeight = 20.dp

    Column(modifier = modifier) {

        // # Title
        Text(
            text = title,
            color = strokes,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // # Input Field
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    color = hint_color,
                    fontWeight = FontWeight.Normal
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = if (isFocused) primary else strokes, // focus-dependent color
                    shape = RoundedCornerShape(12.dp)
                ),
            shape = RoundedCornerShape(12.dp),
            interactionSource = interactionSource,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = inputfield_bg,
                unfocusedContainerColor = inputfield_bg,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedTextColor = strokes,
                unfocusedTextColor = strokes
            ),
            singleLine = true
        )

        // # error message below the field (right-side)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(errorTextHeight)
                .padding(top = 4.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            if (has_error && error_message.isNotBlank()) {
                Text(
                    text = error_message,
                    color = error_message_color,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}