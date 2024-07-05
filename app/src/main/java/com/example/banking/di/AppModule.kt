package com.example.banking.di

import android.app.Application
import androidx.room.Room
import com.example.banking.data.db.AccountsDatabase
import com.example.banking.data.db.migration.MIGRATION_1_2
import com.example.banking.data.repository_impl.AccountsRepositoryImpl
import com.example.banking.data.repository_impl.TransactionRepositoryImpl
import com.example.banking.domain.repository.AccountsRepository
import com.example.banking.domain.repository.TransactionRepository
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
        ).addMigrations(MIGRATION_1_2).build()
    }

    @Provides
    @Singleton
    fun provideAccountRepository(db: AccountsDatabase): AccountsRepository {
        return AccountsRepositoryImpl(db.accountDao())
    }

    @Provides
    @Singleton
    fun provideTransactionRepository(db: AccountsDatabase): TransactionRepository {
        return TransactionRepositoryImpl(db.transactionDao())
    }
}