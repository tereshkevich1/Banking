package com.example.banking.create_transaction_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.banking.R
import com.example.banking.ui.theme.BankingTheme

@Composable
fun TextFieldWithLabel(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit = {},
    readOnly: Boolean = false,
    trailingIcon: @Composable (() -> Unit) = {},
    focusedIndicatorColor: Color = colorResource(id = R.color.clickable_text_color),
    unfocusedIndicatorColor: Color = colorResource(id = R.color.white)
) {
    val bottomPadding = dimensionResource(id = R.dimen.small_padding)
    val textFieldBottomPadding = dimensionResource(id = R.dimen.inner_padding)
    val unfocusedContainerColor = colorResource(id = R.color.surface_background_color)
    val focusedContainerColor = unfocusedContainerColor.copy(alpha = 0.8f)

    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(bottom = bottomPadding)
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = textFieldBottomPadding)
                .height(48.dp)
                .clickable { onItemClick() },
            readOnly = readOnly,
            textStyle = MaterialTheme.typography.bodySmall,
            trailingIcon = trailingIcon,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = unfocusedIndicatorColor,
                focusedIndicatorColor = focusedIndicatorColor,
                unfocusedContainerColor = unfocusedContainerColor,
                focusedContainerColor = focusedContainerColor,
                cursorColor = focusedIndicatorColor
            )
        )
    }
}

@Composable
@Preview
fun TextFieldWithLabelPreview() {
    BankingTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = colorResource(id = R.color.surface_background_color)
        ) {
            Box(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.inner_padding)),
                contentAlignment = Alignment.Center
            ) {
                TextFieldWithLabel("name", "", {}, Modifier)
            }
        }
    }
}
