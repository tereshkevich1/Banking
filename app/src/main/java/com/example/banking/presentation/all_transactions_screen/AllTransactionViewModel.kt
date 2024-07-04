package com.example.banking.presentation.all_transactions_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banking.data.data_source.Transaction
import com.example.banking.domain.use_case.FilterTransactionsByDateUseCase
import com.example.banking.domain.use_case.GetTransactionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllTransactionViewModel @Inject constructor(
    private val getTransactions: GetTransactionsUseCase,
    private val filterTransactionsByDate: FilterTransactionsByDateUseCase
) : ViewModel() {
    var currentAccountId: Int? = null
        set(value) {
            field = value
            loadTransactions()
        }

    private val _originalTransactions = MutableStateFlow<List<Transaction>>(emptyList())
    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions = _transactions.asStateFlow()

    private var transactionJob: Job? = null

    private fun loadTransactions() {
        val accountId = currentAccountId
        transactionJob?.cancel()
        accountId?.let {
            transactionJob = viewModelScope.launch {
                getTransactions(accountId)
                    .flowOn(Dispatchers.IO)
                    .collect { transactions ->
                        _originalTransactions.emit(transactions)
                        _transactions.emit(transactions)
                    }
            }
        }
    }

    fun filterTransactionsByDate(startDate: Long, endDate: Long) {
        viewModelScope.launch {
            val filteredTransactions = filterTransactionsByDate(startDate, endDate, _originalTransactions.value)
            _transactions.emit(filteredTransactions)
        }
    }
}
