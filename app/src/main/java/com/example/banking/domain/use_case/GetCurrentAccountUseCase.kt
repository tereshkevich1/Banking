package com.example.banking.domain.use_case

import com.example.banking.domain.repository.AccountsRepository
import javax.inject.Inject

class GetCurrentAccountUseCase @Inject constructor(private val repository: AccountsRepository) {
    suspend operator fun invoke(currentCard: Boolean) = repository.getCurrentAccount(currentCard)
}