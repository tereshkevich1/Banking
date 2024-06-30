@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.example.banking.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.banking.accounts_screen.AccountsScreen
import com.example.banking.all_transactions_screen.AllTransactionsScreen
import com.example.banking.create_transaction_screen.CreateTransactionsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetUpNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.Accounts.route) {
        composable(route = Screen.Accounts.route) {
            AccountsScreen(navController = navController)
        }
        composable(route = Screen.AllTransactions.route) {
            AllTransactionsScreen(navController = navController)
        }
        composable(route = Screen.CreateTransaction.route) {
            CreateTransactionsScreen(navController = navController)
        }
    }
}