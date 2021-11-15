package com.example.zeytunpharma.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.zeytunpharma.data.Medicine

@Dao
abstract class MedicineDAO {


    @Query("SELECT * FROM medicine WHERE code = :code")
    abstract suspend fun getMedicineWithCode(code: String): Medicine?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(entity: Medicine): Long

}