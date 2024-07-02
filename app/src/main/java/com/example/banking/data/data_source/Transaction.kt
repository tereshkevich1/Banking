package com.example.banking.data.data_source

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.banking.presentation.models.CardState

@Entity(tableName = "transactions",
    foreignKeys = [ForeignKey(entity = Account::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("account_id"),
        onDelete = ForeignKey.CASCADE)])
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "account_id")
    val accountId: Int,
    @ColumnInfo(name = "company_name")
    val companyName: String,
    @ColumnInfo(name = "transaction_date")
    val date: Long,
    @ColumnInfo(name = "transaction_amount")
    val amount: Long,
    @ColumnInfo(name = "transaction_state")
    val state: CardState
)