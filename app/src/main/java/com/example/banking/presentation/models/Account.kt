package com.example.banking.presentation.models

data class Account(
    val name: String,
    val accountNumber: String,
    val cardNumber: String,
    val currentCard: Boolean
)
