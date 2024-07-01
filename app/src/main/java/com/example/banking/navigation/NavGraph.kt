@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.example.banking.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.banking.accounts_screen.AccountsScreen
import com.example.banking.all_transactions_screen.AllTransactionsScreen
import com.example.banking.all_transactions_screen.TransactionViewModel
import com.example.banking.create_transaction_screen.CreateTransactionsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        modifier = Modifier.fillMaxSize(),
        startDestination = Screen.Accounts.route
    ) {
        composable(
            route = Screen.Accounts.route,
            enterTransition = { fadeIn(tween(1000)) },
            exitTransition = { exitSlideTransition() },
            popEnterTransition = { popEnterSlideTransition() }
        ) {
            AccountsScreen(navController = navController)
        }

        composable(
            route = Screen.AllTransactions.route,
            enterTransition = { enterSlideTransition() },
            popExitTransition = { popExitSlideTransition() }
        ) {
            val parentEntry = remember { navController.getBackStackEntry(Screen.Accounts.route) }
            val transactionViewModel: TransactionViewModel = hiltViewModel(parentEntry)
            AllTransactionsScreen(
                navController = navController,
                transactionsViewModel = transactionViewModel
            )
        }

        composable(
            route = Screen.CreateTransaction.route,
            enterTransition = { enterSlideTransition() },
            popExitTransition = { popExitSlideTransition() }
        ) {
            val parentEntry = remember { navController.getBackStackEntry(Screen.Accounts.route) }
            val transactionViewModel: TransactionViewModel = hiltViewModel(parentEntry)
            CreateTransactionsScreen(
                navController = navController,
                transactionsViewModel = transactionViewModel
            )
        }
    }
}



