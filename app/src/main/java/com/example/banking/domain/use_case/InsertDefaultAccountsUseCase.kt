package com.example.banking.domain.use_case

import com.example.banking.data.data_source.Account
import com.example.banking.data.data_source.Transaction
import com.example.banking.domain.repository.AccountsRepository
import com.example.banking.domain.repository.TransactionRepository
import com.example.banking.presentation.models.CardState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

//Hard coding
class InsertDefaultAccountsUseCase @Inject constructor(
    private val accountsRepository: AccountsRepository,
    private val transactionRepository: TransactionRepository
) {
    suspend operator fun invoke() {
        withContext(Dispatchers.IO) {
            val newAccount = Account(
                id = 0,
                accountName = "Default Account 1",
                accountNumber = "1234567890",
                cardNumber = "1111222233334444",
                currentCard = true
            )
            val id = accountsRepository.insertAccountWithId(newAccount)
            val transactions = listOf(
                Transaction(
                    0,
                    id.toInt(),
                    "Google",
                    System.currentTimeMillis(),
                    1090,
                    CardState.EXECUTED,
                    "23572835segi285235"
                ),
                Transaction(
                    0,
                    id.toInt(),
                    "Amazon",
                    System.currentTimeMillis(),
                    500,
                    CardState.EXECUTED,
                    "23572835segi285235"
                ),
                Transaction(
                    0,
                    id.toInt(),
                    "Apple",
                    System.currentTimeMillis(),
                    700,
                    CardState.EXECUTED,
                    "23572835segi285235"
                ),
                Transaction(
                    0,
                    id.toInt(),
                    "Netflix",
                    System.currentTimeMillis(),
                    150,
                    CardState.EXECUTED,
                    "23572835segi285235"
                ),
                Transaction(
                    0,
                    id.toInt(),
                    "Spotify",
                    System.currentTimeMillis(),
                    100,
                    CardState.EXECUTED,
                    "23572835segi285235"
                ),
                Transaction(
                    0,
                    id.toInt(),
                    "Uber",
                    System.currentTimeMillis(),
                    300,
                    CardState.EXECUTED,
                    "23572835segi285235"
                )
            )

            for (transaction in transactions) {
                transactionRepository.insertTransaction(transaction)
            }
        }
    }
}