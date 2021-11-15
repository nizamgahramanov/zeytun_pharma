package com.example.zeytunpharma.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.zeytunpharma.data.User
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserDAO {

    @Query("SELECT * FROM user WHERE email = :email")
    abstract suspend fun getUserWithEmail(email: String): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(entity: User): Long

    @Query("SELECT * FROM user WHERE email = :email")
    abstract fun getUserNameByUserEmail(email: String): Flow<User>

    @Query("SELECT * FROM user WHERE email = :email AND password=:password")
    abstract suspend fun getUserWithEmailAndPassword(email: String, password: String): User?
}