package com.example.banking.domain.use_case

data class TransactionsUseCases(
    val getTransactions: GetTransactionsUseCase,
    val insertTransaction: InsertTransactionUseCase
)