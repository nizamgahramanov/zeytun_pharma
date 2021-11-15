package com.example.zeytunpharma.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.zeytunpharma.data.*

@Database(
    entities = [
        Order::class,
        Category::class,
        Medicine::class,
        OrderMedicine::class,
        Client::class,
        User::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateTimeTypeConverters::class)
abstract class ZeytunPharmaDatabase : RoomDatabase() {
    abstract fun ordersDao(): OrderDAO
    abstract fun transactionRunnerDao(): TransactionRunnerDao
    abstract fun medicineDao(): MedicineDAO
    abstract fun categoryDao(): CategoryDAO
    abstract fun orderMedicineDao(): OrderMedicineDAO
    abstract fun clientDao(): ClientDAO
    abstract fun userDao(): UserDAO
}