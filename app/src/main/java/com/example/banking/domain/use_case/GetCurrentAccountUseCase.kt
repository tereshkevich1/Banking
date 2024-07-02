package com.example.banking.domain.use_case

import com.example.banking.domain.repository.AccountsRepository

class GetCurrentAccountUseCase(private val repository: AccountsRepository) {
    suspend operator fun invoke(currentCard: Boolean) = repository.getCurrentAccount(currentCard)
}