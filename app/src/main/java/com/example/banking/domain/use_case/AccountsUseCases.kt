package com.example.banking.domain.use_case

data class AccountsUseCases(
    val getAccounts: GetAccountsUseCase,
    val getCurrentAccount: GetCurrentAccountUseCase
)