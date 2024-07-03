package com.example.banking.presentation.accounts_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banking.data.data_source.Account
import com.example.banking.data.data_source.Transaction
import com.example.banking.domain.use_case.ChangeCurrentAccountUseCase
import com.example.banking.domain.use_case.GetAccountsUseCase
import com.example.banking.domain.use_case.GetCurrentAccountUseCase
import com.example.banking.domain.use_case.GetTransactionsUseCase
import com.example.banking.domain.use_case.InsertDefaultAccountsUseCase
import com.example.banking.domain.use_case.InsertTransactionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor(
    private val getCurrentAccount: GetCurrentAccountUseCase,
    private val changeCurrentAccount: ChangeCurrentAccountUseCase,
    private val getAccounts: GetAccountsUseCase,
    private val insertDefaultAccounts: InsertDefaultAccountsUseCase,
    private val getTransactions: GetTransactionsUseCase,
    private val insertTransaction: InsertTransactionUseCase
) : ViewModel() {
    // to simplify the code
    private val _currentAccount = MutableStateFlow(Account(0, "", "", "", false))
    val currentAccount = _currentAccount.asStateFlow()

    private val _accounts = MutableStateFlow<List<Account>>(emptyList())
    val accounts = _accounts.asStateFlow()

    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions = _transactions.asStateFlow()

    private var transactionJob: Job? = null

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            // to simplify the code
            insertDefaultAccounts.invoke()

            val account = getCurrentAccount(true)
            _currentAccount.emit(account)
            loadTransactions(account.id)
        }
        loadAllAccounts()
    }

    private fun loadAllAccounts() {
        viewModelScope.launch {
            getAccounts()
                .flowOn(Dispatchers.IO)
                .collect { accounts ->
                    _accounts.emit(accounts)
                }
        }
    }

    fun changeCurrentAccount(updatedAccount: Account) {
        viewModelScope.launch {
            changeCurrentAccount(currentAccount.value, updatedAccount)
            _currentAccount.emit(updatedAccount)
            loadTransactions(updatedAccount.id)
        }
    }

    private fun loadTransactions(accountId: Int) {
        transactionJob?.cancel()
        transactionJob = viewModelScope.launch {
            getTransactions(accountId)
                .flowOn(Dispatchers.IO)
                .collect { transactions ->
                    _transactions.emit(transactions)
                }
        }
    }

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            insertTransaction(transaction)
        }
    }
}
