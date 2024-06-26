package com.example.banking.accounts_screen.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.banking.R

@Composable
fun ChevronForwardIcon() {
    Image(
        painter = painterResource(id = R.drawable.chevron_forward),
        contentDescription = "chevron forward",
        modifier = Modifier.size(16.dp)
    )
}