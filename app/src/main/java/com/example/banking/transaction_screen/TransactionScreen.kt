package com.example.banking.transaction_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.banking.R
import com.example.banking.ui.theme.BankingTheme

@Composable
fun TransactionScreen(){
    Column {
        
    }

}

@Composable
@Preview
fun AccountsScreenPreview() {
    BankingTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = colorResource(id = R.color.surface_background_color)
        ) {
            TransactionScreen()
        }
    }
}