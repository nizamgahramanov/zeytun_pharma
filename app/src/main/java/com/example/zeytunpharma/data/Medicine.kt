package com.example.zeytunpharma.data

import androidx.compose.runtime.Immutable
import androidx.room.*

@Entity(
    tableName = "medicine",
    indices = [
        Index("id", unique = true)
    ],
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["category_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ],
)
@Immutable
data class Medicine(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "name")
    val medicine_name: String,
    @ColumnInfo(name = "place")
    val place: String,
    @ColumnInfo(name = "code")
    val code: String,
    @ColumnInfo(name = "feature")
    val feature: String,
    @ColumnInfo(name = "country")
    val country: String,
    @ColumnInfo(name = "category_id")
    val category_id: Long,
)