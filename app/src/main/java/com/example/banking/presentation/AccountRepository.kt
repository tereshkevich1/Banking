package com.example.banking.presentation

import com.example.banking.data.data_source.Account
import com.example.banking.domain.use_case.ChangeCurrentAccountUseCase
import com.example.banking.domain.use_case.GetCurrentAccountUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val getCurrentAccount: GetCurrentAccountUseCase,
    private val changeCurrentAccount: ChangeCurrentAccountUseCase
) {
    private val _currentAccount = MutableStateFlow(
        Account(
            0,
            "",
            "",
            "",
            false
        )
    )
    val currentAccount = _currentAccount.asStateFlow()

    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        loadCurrentAccount()
    }

    private fun loadCurrentAccount() {
        scope.launch {
            val account = getCurrentAccount(true)
            _currentAccount.emit(account)
        }
    }

    fun updateCurrentAccount(updatedAccount: Account) {
        scope.launch {
            changeCurrentAccount(_currentAccount.value, updatedAccount)
            _currentAccount.emit(updatedAccount)
        }
    }
}
