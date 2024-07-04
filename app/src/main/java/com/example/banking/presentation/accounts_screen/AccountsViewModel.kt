package com.example.banking.presentation.accounts_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banking.data.db.entity.Account
import com.example.banking.data.db.entity.Transaction
import com.example.banking.domain.use_case.ChangeCurrentAccountUseCase
import com.example.banking.domain.use_case.GetAccountsUseCase
import com.example.banking.domain.use_case.GetCurrentAccountUseCase
import com.example.banking.domain.use_case.GetLastTransactionsUseCase
import com.example.banking.domain.use_case.InsertDefaultAccountsUseCase
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
    private val getLastTransactionsUseCase: GetLastTransactionsUseCase,
) : ViewModel() {
    // to simplify the code
    private val _currentAccount = MutableStateFlow(Account(0, "", "", "", false))
    val currentAccount = _currentAccount.asStateFlow()

    private val _accounts = MutableStateFlow<List<Account>>(emptyList())
    val accounts = _accounts.asStateFlow()

    private val _lastTransactions = MutableStateFlow<List<Transaction>>(emptyList())
    val lastTransactions = _lastTransactions.asStateFlow()

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
            getLastTransactionsUseCase(accountId, 4)
                .flowOn(Dispatchers.IO)
                .collect { transactions ->
                    _lastTransactions.emit(transactions)
                }
        }
    }
}
