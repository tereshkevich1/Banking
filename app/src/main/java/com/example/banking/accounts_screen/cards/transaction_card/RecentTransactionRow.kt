package com.example.banking.accounts_screen.cards.transaction_card

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.banking.R

@Composable
fun RecentTransactionRow() {
    val innerPadding = dimensionResource(id = R.dimen.inner_padding)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = innerPadding)
    ) {
        Text(
            text = stringResource(id = R.string.recent_transactions),
            style = MaterialTheme.typography.titleMedium,
        )

        Spacer(modifier = Modifier.weight(1f))

        val textStyle =
            MaterialTheme.typography.bodySmall.merge(TextStyle(color = colorResource(id = R.color.clickable_text_color)))

        ClickableText(
            text = AnnotatedString(stringResource(id = R.string.view_all)),
            modifier = Modifier.align(Alignment.CenterVertically),
            style = textStyle
        ) {
        }
    }
}