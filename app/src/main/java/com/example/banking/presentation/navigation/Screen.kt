package com.example.banking.presentation.navigation

sealed class Screen (val route: String){
    data object Accounts : Screen("accounts")
    data object AllTransactions : Screen("all_transactions")
    data object CreateTransaction : Screen("create_transaction")
    data object Details : Screen("details")
}