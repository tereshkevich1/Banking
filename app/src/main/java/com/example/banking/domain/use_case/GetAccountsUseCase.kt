package com.example.banking.domain.use_case

import com.example.banking.domain.repository.AccountsRepository
import javax.inject.Inject

class GetAccountsUseCase @Inject constructor(private val repository: AccountsRepository) {
    operator fun invoke() = repository.getAccounts()
}