package com.example.banking.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.banking.R



@Composable
fun AccountsScreen() {
    Column {
        Text(text = stringResource(id = R.string.accounts))
    }
}


@Composable
@Preview
fun AccountsScreenPreview() {
    BankingTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            AccountsScreen()
        }
    }
}