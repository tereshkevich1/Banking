package com.example.banking.models

import java.util.Date

data class Transaction(
    val companyName: String,
    val date: Date,
    val amount: Long,
    val state: CardState
)