package com.example.zeytunpharma.data

import android.util.Log
import com.example.zeytunpharma.data.room.TransactionRunner
import com.example.zeytunpharma.data.room.UserDAO
import kotlinx.coroutines.flow.Flow

class UserStore(
    private val userDao: UserDAO,
    private val transactionRunner: TransactionRunner,
) {
    suspend fun addUser(user: User): Long {
        return when (val local = userDao.getUserWithEmail(user.email)) {
            null -> userDao.insert(user)
            else -> local.id
        }
    }

    suspend fun getUserByEmailAndPassword(email: String, password: String): User? {
        Log.e("getUserByEmail", email)
        val user = userDao.getUserWithEmailAndPassword(email, password)
        Log.e("USERRRR ", "S" + user.toString())
        return user
    }

    fun getUserNameByUserEmail(email: String): Flow<User> {
        Log.e("getUserNameByU", email.toString())
        return userDao.getUserNameByUserEmail(email)
    }
}