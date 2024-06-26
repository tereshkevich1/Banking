package com.example.banking.accounts_screen.cards.account

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.banking.R
import com.example.banking.accounts_screen.cards.ChevronForwardIcon
import com.example.banking.models.Account
import com.example.banking.ui.theme.BankingTheme

@Composable
fun CardAccount(
    modifier: Modifier,
    cardBackgroundColor: Color,
    account: Account,
    onCardClick: (Account) -> Unit
) {
    val cardInnerPadding = dimensionResource(id = R.dimen.card_inner_padding)
    val cardImageEndPadding = dimensionResource(id = R.dimen.card_image_end_padding)
    val bottomTextCardPadding = dimensionResource(id = R.dimen.bottom_text_card_padding)
    val textColor = Color.White.copy(alpha = 0.6f)

    Card(
        onClick = { onCardClick(account) },
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(containerColor = cardBackgroundColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(cardInnerPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AccountImage(cardImageEndPadding, Modifier.align(Alignment.Top))
            AccountDetails(
                cardName = account.name,
                accountNumber = account.accountNumber,
                cardNumber = account.cardNumber,
                textColor = textColor,
                bottomTextCardPadding = bottomTextCardPadding
            )
            Spacer(modifier = Modifier.weight(1f))
            ChevronForwardIcon()
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
fun CardAccountPreview() {
    BankingTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            val color = colorResource(id = R.color.account_card_background_color)
            CardAccount(
                Modifier,
                color,
                Account(
                    "Saving",
                    "91212192291221",
                    "1234123412341234", false
                ),
                {})
        }
    }
}
