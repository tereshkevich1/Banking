package com.example.banking.transcation_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.banking.R

@Composable
fun OkButton(onClick: () -> Unit, modifier: Modifier, isButtonEnabled: Boolean) {
    val containerColor = colorResource(id = R.color.floating_button_container_color)
    val disabledContainerColor = containerColor.copy(alpha = 0.3f)
    val textColor = if (isButtonEnabled) {
        Color.White
    } else {
        Color.White.copy(alpha = 0.5f)
    }

    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        enabled = isButtonEnabled,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            disabledContainerColor = disabledContainerColor
        )
    ) {
        Text(
            text = stringResource(id = R.string.ok),
            color = textColor,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold
        )
    }
}