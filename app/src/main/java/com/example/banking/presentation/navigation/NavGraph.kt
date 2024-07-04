@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.banking.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.banking.presentation.accounts_screen.AccountsScreen
import com.example.banking.presentation.accounts_screen.AccountsViewModel
import com.example.banking.presentation.all_transactions_screen.AllTransactionViewModel
import com.example.banking.presentation.all_transactions_screen.AllTransactionsScreen
import com.example.banking.presentation.common_vm.SharedTransactionViewModel
import com.example.banking.presentation.create_transaction_screen.CreateTransactionViewModel
import com.example.banking.presentation.create_transaction_screen.CreateTransactionsScreen

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
            val parentEntry = remember { navController.getBackStackEntry(Screen.Accounts.route) }
            val accountsViewModel: AccountsViewModel = hiltViewModel(parentEntry)
            AccountsScreen(
                navController = navController,
                accountsViewModel = accountsViewModel
            )
        }

        composable(
            route = Screen.AllTransactions.route,
            arguments = listOf(navArgument(ARG_ACCOUNT_ID) { type = NavType.StringType }),
            enterTransition = { enterSlideTransition() },
            popExitTransition = { popExitSlideTransition() }
        ) { backStackEntry ->
            val accountId = backStackEntry.arguments?.getString(ARG_ACCOUNT_ID)?.toIntOrNull()
            val allTransactionViewModel: AllTransactionViewModel = hiltViewModel()
            allTransactionViewModel.currentAccountId = accountId

            val parentEntry = remember { navController.getBackStackEntry(Screen.Accounts.route) }
            val sharedTransactionViewModel: SharedTransactionViewModel = hiltViewModel(parentEntry)
            AllTransactionsScreen(
                navController = navController,
                sharedTransactionViewModel = sharedTransactionViewModel,
                allTransactionViewModel = allTransactionViewModel
            )
        }

        composable(
            route = Screen.CreateTransaction.route,
            arguments = listOf(navArgument(ARG_ACCOUNT_ID) {
                type = NavType.StringType
            }),
            enterTransition = { enterSlideTransition() },
            popExitTransition = { popExitSlideTransition() }
        ) { backStackEntry ->
            val accountId = backStackEntry.arguments?.getString(ARG_ACCOUNT_ID)?.toIntOrNull()
            val createTransactionViewModel: CreateTransactionViewModel = hiltViewModel()
            createTransactionViewModel.currentAccountId = accountId

            val parentEntry =
                remember { navController.getBackStackEntry(Screen.Accounts.route) }
            val sharedTransactionViewModel: SharedTransactionViewModel =
                hiltViewModel(parentEntry)
            CreateTransactionsScreen(
                navController = navController,
                createTransactionViewModel = createTransactionViewModel,
                sharedTransactionViewModel = sharedTransactionViewModel
            )
        }
    }
}

