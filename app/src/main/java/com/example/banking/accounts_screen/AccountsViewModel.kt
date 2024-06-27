package com.example.banking.accounts_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banking.models.Account
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AccountsViewModel : ViewModel() {
    private val _account = MutableStateFlow(Account("name", "1241244132525", "134234235", true))
    val account = _account.asStateFlow()

    fun loadAccount() {
        viewModelScope.launch {
            _account.emit(Account("name", "1241244132525", "134234235", true))
        }
    }

    fun updateAccount(updatedAccount: Account) {
        _account.value = updatedAccount
    }
}