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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.banking.ui.theme.BankingTheme

@Composable
fun CardAccount(
    cardName: String,
    accountNumber: String,
    cardNumber: String,
    onCardClick: () -> Unit
) {

    Card(
        onClick = onCardClick, modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.account_card_background_color))
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.card_inner_padding)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.card),
                contentDescription = "account image",
                modifier = Modifier
                    .width(40.dp)
                    .height(25.dp)
                    .align(Alignment.Top)
                    .clip(RoundedCornerShape(2.dp))
                    .padding(end = dimensionResource(id = R.dimen.card_image_end_padding))
            )

            Column {
                Text(
                    text = cardName,
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.bottom_text_card_padding))
                )
                Text(
                    text = accountNumber,
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.bottom_text_card_padding))
                )
                Text(text = "•••• 1234")
            }

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.chevron_forward),
                contentDescription = "chevron forward",
                modifier = Modifier.size(16.dp)
            )
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
            CardAccount("Saving", "91212192291221", "1234123412341234", {})
        }
    }
}