package com.example.zeytunpharma.data

import com.example.zeytunpharma.data.room.CategoryDAO
import com.example.zeytunpharma.data.room.TransactionRunner

class CategoryStore(
    private val categoryDao: CategoryDAO,
    private val transactionRunner: TransactionRunner,
) {
    suspend fun addCategory(category: Category): Long {
        return when (val local = categoryDao.getCategoryWithNumber(category.number)) {
            null -> categoryDao.insert(category)
            else -> local.id
        }
    }
}