package com.example.banking.domain.use_case

import com.example.banking.data.db.entity.Account
import com.example.banking.domain.repository.AccountsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChangeCurrentAccountUseCase @Inject constructor(private val accountsRepository: AccountsRepository) {
    suspend operator fun invoke(currentAccount: Account, newCurrentAccount: Account) {
        withContext(Dispatchers.IO) {
            currentAccount.currentCard = false
            newCurrentAccount.currentCard = true
            accountsRepository.updateAccount(currentAccount)
            accountsRepository.updateAccount(newCurrentAccount)
        }
    }
}