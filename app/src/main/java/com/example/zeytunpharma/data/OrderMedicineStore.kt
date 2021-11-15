package com.example.zeytunpharma.data

import com.example.zeytunpharma.data.room.OrderMedicineDAO
import com.example.zeytunpharma.data.room.TransactionRunner
import kotlinx.coroutines.flow.Flow

class OrderMedicineStore(
    private val orderMedicineDao: OrderMedicineDAO,
    private val transactionRunner: TransactionRunner,
) {
    suspend fun addOrderMedicine(order: Long, medicine: Long) {
        orderMedicineDao.insert(
            OrderMedicine(order, medicine, 5000),
        )
    }

    fun getMedicinesByOrderId(orderId: Long): Flow<List<OrderToMedicine>> {
        val c = orderMedicineDao.getMedicinesByOrderId(orderId)
        return c
    }
}