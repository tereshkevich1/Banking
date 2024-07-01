package com.example.banking.all_transactions_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banking.models.CardState
import com.example.banking.models.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor() : ViewModel() {
    private val _transactions by lazy { MutableStateFlow<List<Transaction>>(emptyList()) }
    val transactions = _transactions.asStateFlow()

    init {
        loadTransactions()
    }

    fun addTransaction(transaction: Transaction) {
        val currentTransactions = _transactions.value.toMutableList()
        currentTransactions.add(transaction)
        viewModelScope.launch {
            _transactions.emit(currentTransactions)
        }
    }

    fun loadTransactions() {
        viewModelScope.launch {
            _transactions.emit(
                listOf(
                    Transaction("Google", Date(), 1000, CardState.EXECUTED),
                    Transaction("Google", Date(), 1000, CardState.DECLINED),
                    Transaction(
                        "Google",
                        Date(),
                        1000,
                        CardState.IN_PROGRESS
                    ),
                    Transaction("Google", Date(), 1000, CardState.EXECUTED),
                    Transaction("Google", Date(), 1000, CardState.EXECUTED),
                    Transaction("Google", Date(), 1000, CardState.EXECUTED),
                    Transaction("Google", Date(), 1000, CardState.EXECUTED),
                    Transaction("Google", Date(), 1000, CardState.EXECUTED)
                )
            )
        }
    }
}