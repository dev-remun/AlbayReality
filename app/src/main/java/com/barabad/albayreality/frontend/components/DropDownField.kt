package com.barabad.albayreality.frontend.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barabad.albayreality.ui.theme.primary
import com.barabad.albayreality.ui.theme.strokes
import com.barabad.albayreality.ui.theme.inputfield_bg
import com.barabad.albayreality.ui.theme.error_message_color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownField(
    title: String,
    value: String,
    options: List<String>,
    placeholder: String,
    isError: Boolean,
    errorMessage: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val error_text_height = 18.dp

    Column(modifier = modifier) {
        // # Title
        Text(
            text = title,
            color = strokes,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // # Dropdown Box
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = {},
                readOnly = true,
                placeholder = { Text(placeholder, color = strokes.copy(alpha = 0.5f)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .border(2.dp, if (expanded) primary else strokes, RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = inputfield_bg,
                    unfocusedContainerColor = inputfield_bg,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedTextColor = strokes,
                    unfocusedTextColor = strokes,
                    focusedTrailingIconColor = strokes,
                    unfocusedTrailingIconColor = strokes
                )
            )

            // # Dropdown Menu Items
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(Color.White)
            ) {
                options.forEach { selection_option ->
                    DropdownMenuItem(
                        text = { Text(selection_option, color = strokes) },
                        onClick = {
                            // # Send the selected value up to the parent
                            onValueChange(selection_option)
                            // # Close the dropdown
                            expanded = false
                        }
                    )
                }
            }
        }

        // # Error Message Space
        Spacer(modifier = Modifier.height(4.dp))
        Box(modifier = Modifier.fillMaxWidth().height(error_text_height)) {
            if (isError) {
                Text(
                    text = errorMessage,
                    color = error_message_color,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
        }
    }
}