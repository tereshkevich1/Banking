package com.example.banking.accounts_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banking.models.Account
import com.example.banking.models.CardState
import com.example.banking.models.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor() : ViewModel() {
    private val _account = MutableStateFlow(Account("name", "1241244132525", "134234235", true))
    val account = _account.asStateFlow()

    val accounts = listOf(
        Account("saving Account", "19124214302735", "12847234", true),
        Account("sav Account", "19124214302735", "12847230", false),
        Account("sa Account", "19124214302735", "12847241", false),
        Account("s Account", "19124214302735", "12847342", false),
    )
    val transactions = listOf(
        Transaction("Google", Date(), 1000, CardState.EXECUTED),
        Transaction("Google", Date(), 1000, CardState.DECLINED),
        Transaction(
            "Google",
            Date(),
            1000,
            CardState.IN_PROGRESS
        ),
        Transaction("Google", Date(), 1000, CardState.EXECUTED)
    )

    fun loadAccount() {
        viewModelScope.launch {
            _account.emit(Account("name", "1241244132525", "134234235", true))
        }
    }

    fun updateAccount(updatedAccount: Account) {
        _account.value = updatedAccount
    }
}