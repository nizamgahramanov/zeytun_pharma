package com.example.zeytunpharma.data

import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "user",
    indices = [
        Index("id", "email", unique = true)
    ],
)
@Immutable
data class User(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "password")
    val password: String,

    )
