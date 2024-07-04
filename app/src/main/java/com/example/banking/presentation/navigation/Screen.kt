package com.example.banking.presentation.navigation

sealed class Screen (val route: String){
    data object Accounts : Screen("accounts")
    data object AllTransactions : Screen("all_transactions/{$ARG_ACCOUNT_ID}")
    data object CreateTransaction : Screen("create_transaction/{$ARG_ACCOUNT_ID}")
}

const val ARG_ACCOUNT_ID = "accountId"