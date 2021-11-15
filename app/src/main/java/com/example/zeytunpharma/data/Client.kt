package com.example.zeytunpharma.data

import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "client",
    indices = [
        Index("id", unique = true)
    ],
)
@Immutable
data class Client(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,

    @ColumnInfo(name = "client_number")
    val client_number: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "name")
    val name: String,
)
