package com.example.zeytunpharma.data

import androidx.room.ColumnInfo

data class OrderToMedicine(
    @ColumnInfo(name = "id")
    val id: Long,
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
    @ColumnInfo(name = "number")
    val number: String,
    @ColumnInfo(name = "cat_name")
    val cat_name: String,
    @ColumnInfo(name = "medicine_count")
    var medicine_count: Long,
)
//        @Embedded
//        lateinit var order:Order
//
//        @Relation(
//                parentColumn = "id",
//                entity=Medicine::class,
//                entityColumn = "id",
//                associateBy = Junction(value = OrderMedicine::class) ,
//        )
//        lateinit var medicine: List<Medicine>
//
//        @ColumnInfo(name = "medicine_count")
//        var medicineCount: Long? =null
