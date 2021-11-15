package com.example.zeytunpharma.data

import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
    tableName = "category",
    indices = [
        Index("id", unique = true)
    ]
)
@Immutable
data class Category(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "number")
    val number: String,
    @ColumnInfo(name = "name")
    val name: String,
)