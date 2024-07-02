package com.example.banking.presentation.accounts_screen.bottomSheet

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.banking.R
import com.example.banking.presentation.models.Account

@Composable
fun getCardBackgroundColor(item: Account, currentAccount: Account): Color {
    val targetValue = if (item == currentAccount) {
        colorResource(id = R.color.current_card_background_color)
    } else {
        colorResource(id = R.color.account_card_background_color)
    }
    val color by animateColorAsState(
        targetValue = targetValue,
        animationSpec = tween(durationMillis = 500),
        label = ""
    )
    return color
}