package com.example.banking.presentation.accounts_screen.components.cards.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.banking.R

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