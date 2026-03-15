package com.example.chat.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.theme.AppTheme


@Composable
fun MessageInputArea() {
    val LightBlueBackground = AppTheme.colorScheme.primary
    val InputFieldBorder = AppTheme.colorScheme.secondary
    val VoiceButtonBackground = AppTheme.colorScheme.third

    var messageText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().background(LightBlueBackground),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            TextField(
                value = "",
                onValueChange = {},
                label = { Text("Enter text") },
                modifier = Modifier
                    .background(
                        Color.LightGray,
                        shape = RoundedCornerShape(16.dp)
                    ) // Rounded corners without border
                    .padding(16.dp)
            )


        Spacer(modifier = Modifier.width(8.dp))

        FloatingActionButton(
            onClick = { },
            modifier = Modifier.size(48.dp),
            containerColor = VoiceButtonBackground,
            shape = CircleShape
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = "Voice Input",
                tint = Color.White
            )
        }
    }
    }
}
