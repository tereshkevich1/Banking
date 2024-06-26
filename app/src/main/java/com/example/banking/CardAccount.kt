package com.example.banking

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.banking.ui.theme.BankingTheme

@Composable
fun CardAccount(
    cardName: String,
    accountNumber: String,
    cardNumber: String,
    onCardClick: () -> Unit
) {
    val cardBackgroundColor = colorResource(id = R.color.account_card_background_color)
    val cardInnerPadding = dimensionResource(id = R.dimen.card_inner_padding)
    val cardImageEndPadding = dimensionResource(id = R.dimen.card_image_end_padding)
    val bottomTextCardPadding = dimensionResource(id = R.dimen.bottom_text_card_padding)
    val textColor = Color.White.copy(alpha = 0.6f)

    Card(
        onClick = onCardClick,
        modifier = Modifier
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
                cardName = cardName,
                accountNumber = accountNumber,
                cardNumber = cardNumber,
                textColor = textColor,
                bottomTextCardPadding = bottomTextCardPadding
            )
            Spacer(modifier = Modifier.weight(1f))
            ChevronForwardIcon()
        }
    }
}

@Composable
fun AccountImage(paddingEnd: Dp, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.card),
        contentDescription = "account image",
        modifier = modifier
            .width(40.dp)
            .height(25.dp)
            .clip(RoundedCornerShape(2.dp))
            .padding(end = paddingEnd)
    )
}

@Composable
fun AccountDetails(
    cardName: String,
    accountNumber: String,
    cardNumber: String,
    textColor: Color,
    bottomTextCardPadding: Dp
) {
    Column {
        Text(
            text = cardName,
            modifier = Modifier.padding(bottom = bottomTextCardPadding),
            style = MaterialTheme.typography.bodySmall,
            fontSize = 20.sp
        )
        Text(
            text = accountNumber,
            modifier = Modifier.padding(bottom = bottomTextCardPadding),
            style = MaterialTheme.typography.bodySmall,
            color = textColor
        )
        Text(
            text = "•••• ${cardNumber.takeLast(4)}",
            style = MaterialTheme.typography.bodySmall,
            color = textColor
        )
    }
}

@Composable
fun ChevronForwardIcon() {
    Image(
        painter = painterResource(id = R.drawable.chevron_forward),
        contentDescription = "chevron forward",
        modifier = Modifier.size(16.dp)
    )
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
            CardAccount("Saving", "91212192291221", "1234123412341234", {})
        }
    }
}
