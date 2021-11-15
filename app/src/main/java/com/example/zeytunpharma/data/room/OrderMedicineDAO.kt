package com.example.zeytunpharma.data.room

import androidx.room.*
import com.example.zeytunpharma.data.OrderMedicine
import com.example.zeytunpharma.data.OrderToMedicine
import kotlinx.coroutines.flow.Flow

@Dao
abstract class OrderMedicineDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(entity: OrderMedicine): Long

    @Transaction
    @Query(
        """
        SELECT medicine.*,medicine_count,category.number,category.name as cat_name FROM order_medicine 
        INNER JOIN  medicine ON medicine.id = order_medicine.medicine_id 
        INNER JOIN category ON medicine.category_id=category.id
        WHERE order_medicine.order_id=:orderId
        """
    )
    abstract fun getMedicinesByOrderId(orderId: Long): Flow<List<OrderToMedicine>>


}