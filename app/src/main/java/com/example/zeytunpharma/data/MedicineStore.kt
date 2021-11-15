package com.example.zeytunpharma.data

import com.example.zeytunpharma.data.room.MedicineDAO
import com.example.zeytunpharma.data.room.TransactionRunner


class MedicineStore(
    private val medicineDao: MedicineDAO,
    private val transactionRunner: TransactionRunner,
) {

    suspend fun addMedicine(medicine: Medicine): Long {
        return when (val local = medicineDao.getMedicineWithCode(medicine.code)) {
            null -> medicineDao.insert(medicine)
            else -> local.id
        }
    }

}


