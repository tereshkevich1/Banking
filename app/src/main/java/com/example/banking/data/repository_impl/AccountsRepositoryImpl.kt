package com.example.banking.data.repository_impl

import com.example.banking.data.db.entity.Account
import com.example.banking.data.db.dao.AccountDao
import com.example.banking.domain.repository.AccountsRepository
import kotlinx.coroutines.flow.Flow

class AccountsRepositoryImpl(private val accountDao: AccountDao) : AccountsRepository {
    override fun getAccounts(): Flow<List<Account>> {
        return accountDao.getNotes()
    }

    override suspend fun getCurrentAccount(currentCard: Boolean): Account {
        return accountDao.getCurrentAccount(currentCard)
    }

    override suspend fun insertAccount(account: Account) {
        accountDao.insertAccount(account)
    }

    override suspend fun updateAccount(account: Account) {
        accountDao.updateAccount(account)
    }

    override suspend fun insertAccountWithId(account: Account): Long {
        return accountDao.insertAccountWithId(account)
    }
}