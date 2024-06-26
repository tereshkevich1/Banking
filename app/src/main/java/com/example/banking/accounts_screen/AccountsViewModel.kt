package com.example.banking.accounts_screen

import androidx.lifecycle.ViewModel
import com.example.banking.models.Account
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AccountsViewModel : ViewModel() {
    private val _account = MutableStateFlow(Account("name", "1241244132525", "134234235", true))
    val account: StateFlow<Account> get() = _account

    fun loadAccount() {
        _account.value = Account("name", "1241244132525", "134234235", true)
    }

    fun updateAccount(updatedAccount: Account) {
        _account.value = updatedAccount
    }
}