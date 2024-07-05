package com.example.banking.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.banking.data.db.dao.AccountDao
import com.example.banking.data.db.dao.TransactionDao
import com.example.banking.data.db.entity.Account
import com.example.banking.data.db.entity.Transaction

@Database(entities = [Account::class, Transaction::class], version = 2, exportSchema = false)
abstract class AccountsDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun transactionDao(): TransactionDao

    companion object {
        const val DATABASE_NAME = "accounts_db"
    }
}

