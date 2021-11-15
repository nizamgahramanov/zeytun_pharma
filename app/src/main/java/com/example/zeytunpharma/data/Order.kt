package com.example.zeytunpharma.data

import androidx.compose.runtime.Immutable
import androidx.room.*
import java.time.OffsetDateTime

@Entity(
    tableName = "zeytun_order",
    indices = [
        Index("id", "order_number", unique = true)
    ],
    foreignKeys = [
        ForeignKey(
            entity = Client::class,
            parentColumns = ["id"],
            childColumns = ["client_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ],
)
@Immutable
data class Order(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "order_number")
    val orderNumber: String,
    @ColumnInfo(name = "store_name")
    val store_name: String,
    @ColumnInfo(name = "store_res_person")
    val store_res_person: String,
    @ColumnInfo(name = "deliver_res_person")
    val delivery_res_person: String,
    @ColumnInfo(name = "created_date")
    val created_date: OffsetDateTime,
    @ColumnInfo(name = "is_completed")
    val is_completed: Boolean,
    @ColumnInfo(name = "client_id")
    val client_id: Long,
)
