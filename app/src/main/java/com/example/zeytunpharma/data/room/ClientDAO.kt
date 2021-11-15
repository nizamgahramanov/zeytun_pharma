package com.example.zeytunpharma.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.zeytunpharma.data.Client

@Dao
abstract class ClientDAO {


    @Query("SELECT * FROM client WHERE client_number = :client_number")
    abstract suspend fun getClientWithClientId(client_number: String): Client?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(entity: Client): Long
}