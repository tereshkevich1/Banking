package com.example.banking.data.data_source

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
data class Account(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "account_name")
    val accountName: String,
    @ColumnInfo(name = "account_number")
    val accountNumber: String,
    @ColumnInfo(name = "card_number")
    val cardNumber: String,
    @ColumnInfo(name = "current_card")
    val currentCard: Boolean
)