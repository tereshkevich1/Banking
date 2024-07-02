package com.example.banking.di

import android.app.Application
import androidx.room.Room
import com.example.banking.data.data_source.AccountsDatabase
import com.example.banking.data.repository.AccountsRepository
import com.example.banking.data.repository.AccountsRepositoryImpl
import com.example.banking.data.repository.TransactionRepository
import com.example.banking.data.repository.TransactionRepositoryImpl
import com.example.banking.domain.use_case.AccountsUseCases
import com.example.banking.domain.use_case.GetAccountsUseCase
import com.example.banking.domain.use_case.GetCurrentAccountUseCase
import com.example.banking.domain.use_case.GetTransactionsUseCase
import com.example.banking.domain.use_case.InsertTransactionUseCase
import com.example.banking.domain.use_case.TransactionsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AccountsDatabase {
        return Room.databaseBuilder(
            app,
            AccountsDatabase::class.java,
            AccountsDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideAccountRepository(db: AccountsDatabase): AccountsRepository {
        return AccountsRepositoryImpl(db.accountDao())
    }

    @Provides
    @Singleton
    fun provideAccountsUseCases(repository: AccountsRepository): AccountsUseCases {
        return AccountsUseCases(
            getAccounts = GetAccountsUseCase(repository),
            getCurrentAccount = GetCurrentAccountUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideTransactionRepository(db: AccountsDatabase): TransactionRepository {
        return TransactionRepositoryImpl(db.transactionDao())
    }

    @Provides
    @Singleton
    fun provideTransactionsUseCases(repository: TransactionRepository): TransactionsUseCases {
        return TransactionsUseCases(
            getTransactions = GetTransactionsUseCase(repository),
            insertTransaction = InsertTransactionUseCase(repository)
        )
    }
}