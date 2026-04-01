package com.barabad.albayreality.frontend.components

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barabad.albayreality.R
import com.barabad.albayreality.ui.theme.hint_color
import com.barabad.albayreality.ui.theme.inputfield_bg
import com.barabad.albayreality.ui.theme.primary
import com.barabad.albayreality.ui.theme.strokes
import com.barabad.albayreality.ui.theme.error_message_color

@Composable
fun PasswordInputField(
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
    var isPasswordVisible by remember { mutableStateOf(false) }
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

        // # Password Field
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
                    color = if (isFocused) primary else strokes,
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
            singleLine = true,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        painter = painterResource(
                            id = if (isPasswordVisible)
                                R.drawable.show_visibility_on
                            else
                                R.drawable.show_visibility_off
                        ),
                        contentDescription = "Toggle Password Visibility",
                        tint = strokes,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        )

        // # Error message below the field (right-aligned, reserved space)
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