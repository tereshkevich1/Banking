package com.example.banking.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Account::class], version = 1, exportSchema = false)
abstract class AccountsDatabase: RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun transactionDao(): TransactionDao

    companion object{
        const val DATABASE_NAME = "accounts_db"
    }
}

