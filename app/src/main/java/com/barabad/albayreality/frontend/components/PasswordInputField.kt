package com.barabad.albayreality.frontend.components

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@Composable
fun PasswordInputField(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    val interaction_source = remember { MutableInteractionSource() }
    val is_focused by interaction_source.collectIsFocusedAsState()
    var is_password_visible by remember { mutableStateOf(false) }

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
                    color = if (is_focused) primary else strokes,
                    shape = RoundedCornerShape(12.dp)
                ),
            shape = RoundedCornerShape(12.dp),
            interactionSource = interaction_source,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = inputfield_bg,
                unfocusedContainerColor = inputfield_bg,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedTextColor = strokes,
                unfocusedTextColor = strokes
            ),
            singleLine = true,
            visualTransformation = if (is_password_visible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { is_password_visible = !is_password_visible }) {
                    Icon(
                        painter = painterResource(
                            id = if (is_password_visible)
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
    }
}