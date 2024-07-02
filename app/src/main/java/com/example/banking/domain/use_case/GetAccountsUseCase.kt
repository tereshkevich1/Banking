package com.example.banking.domain.use_case

import com.example.banking.domain.repository.AccountsRepository

class GetAccountsUseCase(private val repository: AccountsRepository) {
    operator fun invoke() = repository.getAccounts()
}