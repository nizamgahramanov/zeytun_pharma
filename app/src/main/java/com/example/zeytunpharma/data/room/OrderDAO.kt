package com.example.zeytunpharma.data.room

import androidx.room.*
import com.example.zeytunpharma.data.Order
import com.example.zeytunpharma.data.OrderToClient
import kotlinx.coroutines.flow.Flow

@Dao
abstract class OrderDAO {

    @Query("SELECT COUNT(*) FROM zeytun_order")
    abstract suspend fun count(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(entity: Order): Long

    @Query("SELECT * FROM zeytun_order WHERE order_number = :number")
    abstract suspend fun getOrderWithNumber(number: String): Order?

    @Query("SELECT * FROM zeytun_order WHERE id = :orderId")
    abstract fun getOrderNameById(orderId: Long): Flow<Order>

    @Transaction
    @Query(
        """
        SELECT * FROM zeytun_order ORDER BY datetime(created_date) DESC LIMIT :limit
        """
    )
    abstract fun getOrders(limit: Int): Flow<List<Order>>

    @Transaction
    @Query(
        """
        SELECT * FROM zeytun_order INNER JOIN client ON client.id=zeytun_order.client_id WHERE date(created_date) = :date AND  order_number Like '%'||:searchText||'%' ORDER BY datetime(created_date) DESC
        """
    )
    abstract fun getOrdersByCurrentTab(date: String?, searchText: String): Flow<List<OrderToClient>>

    @Transaction
    @Query(
        """
        SELECT * FROM zeytun_order INNER JOIN client ON client.id=zeytun_order.client_id WHERE date(created_date) != :date AND order_number Like '%'||:searchText||'%' ORDER BY datetime(created_date) DESC
        """
    )
    abstract fun getOrdersByHistoryTab(date: String?, searchText: String): Flow<List<OrderToClient>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun update(entity: Order)

    @Query(
        """
        UPDATE zeytun_order SET is_completed=1 WHERE id=:orderId 
        """
    )
    abstract suspend fun updateOrderCompleted(orderId: Long)


    //    @Query("SELECT * FROM zeytun_order")
//    abstract suspend fun getOrdersByTabAndSearchText(selectedTab: HomeTabs,searchText:String): List<OrderToClient>
    @Transaction
    @Query(
        """
            SELECT * FROM zeytun_order INNER JOIN client ON client.id=zeytun_order.client_id WHERE date(created_date) != :date AND order_number Like '%'||:searchText||'%' ORDER BY datetime(created_date) DESC
            """
    )
    abstract suspend fun getOrdersByHistoryAndSearchText(
        date: String?,
        searchText: String,
    ): List<OrderToClient>


    @Transaction
    @Query(
        """
            SELECT * FROM zeytun_order INNER JOIN client ON client.id=zeytun_order.client_id WHERE date(created_date) = :date AND order_number Like '%'||:searchText||'%' ORDER BY datetime(created_date) DESC
            """
    )
    abstract suspend fun getOrdersByCurrentAndSearchText(
        date: String?,
        searchText: String,
    ): List<OrderToClient>
}

