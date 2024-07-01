package com.example.banking.presentation.all_transactions_screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banking.R

@Composable
fun NavTopPanel(onBackButtonClick: () -> Unit, onOptionsButtonClick: () -> Unit) {
    val verticalPadding = dimensionResource(id = R.dimen.small_padding)
    val contentPadding = PaddingValues(start = 0.dp, end = 2.dp, top = 0.dp, bottom = 0.dp)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = verticalPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomIconButton(
            icon = ImageVector.vectorResource(id = R.drawable.chevron_backward),
            iconSize = 18.dp,
            contentPadding = contentPadding,
            contentDescription = "chevron backward",
            onClick = onBackButtonClick
        )

        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(id = R.string.all_transactions),
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.weight(1f))

        CustomIconButton(
            icon = ImageVector.vectorResource(id = R.drawable.ellipsis_circle),
            contentDescription = "ellipsis circle",
            onClick = onOptionsButtonClick
        )
    }
}