package com.example.zeytunpharma.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.zeytunpharma.data.Category

@Dao
abstract class CategoryDAO {


    @Query("SELECT * FROM category WHERE number = :number")
    abstract suspend fun getCategoryWithNumber(number: String): Category?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(entity: Category): Long
}