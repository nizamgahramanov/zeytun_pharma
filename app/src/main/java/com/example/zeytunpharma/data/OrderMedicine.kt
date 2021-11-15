package com.example.zeytunpharma.data


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index


@Entity(
    tableName = "order_medicine",
    primaryKeys = ["order_id", "medicine_id"],
    foreignKeys = [
        ForeignKey(
            entity = Order::class,
            parentColumns = ["id"],
            childColumns = ["order_id"],
        ),
        ForeignKey(
            entity = Medicine::class,
            parentColumns = ["id"],
            childColumns = ["medicine_id"],
        )
    ],
    indices = [
        Index("order_id", "medicine_id", unique = true),
        Index("medicine_id"),
        Index("order_id")
    ]

)


data class OrderMedicine(
    @ColumnInfo(name = "order_id") val order_id: Long,
    @ColumnInfo(name = "medicine_id") val medicine_id: Long,
    @ColumnInfo(name = "medicine_count") val medicine_count: Long,
)