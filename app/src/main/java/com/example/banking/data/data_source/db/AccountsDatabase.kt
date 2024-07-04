package com.example.banking.data.data_source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.banking.data.data_source.Account
import com.example.banking.data.data_source.AccountDao
import com.example.banking.data.data_source.Transaction
import com.example.banking.data.data_source.TransactionDao

@Database(entities = [Account::class, Transaction::class], version = 2, exportSchema = false)
abstract class AccountsDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun transactionDao(): TransactionDao

    companion object {
        const val DATABASE_NAME = "accounts_db"
    }
}

