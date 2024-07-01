package com.example.banking.all_transactions_screen.select_date_bottom_sheet

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.banking.R

@Composable
fun DateButtonWithIcon(
    isButtonActive: Boolean,
    hasError: Boolean,
    date: String,
    title: String,
    onClick: () -> Unit
) {
    val dateText = date.ifEmpty { title }

    val textColor =
        if (date.isEmpty()) colorResource(id = R.color.deactivated_text_color) else Color.White

    val borderColor =
        if (isButtonActive) colorResource(id = R.color.clickable_text_color)
        else if (hasError) colorResource(id = R.color.declined_color)
        else colorResource(id = R.color.unfocused_indicator_date_field_color)

    val animatedColor by animateColorAsState(
        targetValue = borderColor,
        tween(300),
        label = "border"
    )

    val verticalPadding = dimensionResource(id = R.dimen.small_padding)
    val dateButtonPadding = dimensionResource(id = R.dimen.date_button_padding)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(vertical = verticalPadding)
            .border(
                BorderStroke(width = 2.dp, color = animatedColor),
                RoundedCornerShape(8.dp)
            )
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = dateText,
            modifier = Modifier.padding(start = dateButtonPadding),
            color = textColor,
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.calendar),
            contentDescription = "Calendar icon",
            modifier = Modifier.padding(end = dateButtonPadding),
            tint = Color.White
        )
    }
}