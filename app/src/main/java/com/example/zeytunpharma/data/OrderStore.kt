package com.example.zeytunpharma.data

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.zeytunpharma.data.room.OrderDAO
import com.example.zeytunpharma.data.room.TransactionRunner
import kotlinx.coroutines.flow.Flow
import java.time.OffsetDateTime

class OrderStore(
    private val orderDao: OrderDAO,
    private val transactionRunner: TransactionRunner,
) {
    suspend fun addOrder(order: Order): Long {
        Log.e("ADD ORDER", order.toString())
        return when (val local = orderDao.getOrderWithNumber(order.orderNumber)) {
            null -> orderDao.insert(order)
            else -> local.id
        }
    }

    fun getOrders(
        limit: Int = Int.MAX_VALUE,
    ): Flow<List<Order>> {
        Log.e("PodcastStore", "followedPodcastsSortedByLastEpisode")
        return orderDao.getOrders(limit)
    }

    fun getOrderNameById(orderId: Long): Flow<Order> {
        Log.e("getOrderNameById", orderId.toString())
        return orderDao.getOrderNameById(orderId)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getOrdersByTab(
        selectedTab: HomeTabs, searchText: String,
    ): Flow<List<OrderToClient>> {

        var dateForQuery = OffsetDateTime.now().toLocalDate().toString()
        Log.e("searchText", "  ____  " + searchText)
        Log.e("getOrdersByTab", "  ____  " + selectedTab.toString())
        return when (selectedTab) {
            HomeTabs.History -> {
                orderDao.getOrdersByHistoryTab(dateForQuery, searchText)
            }
            HomeTabs.Current -> {
                orderDao.getOrdersByCurrentTab(dateForQuery, searchText)
            }
        }

    }

    suspend fun updateOrderCompleted(orderId: Long) {
        orderDao.updateOrderCompleted(orderId)
    }


    suspend fun isEmpty(): Boolean = orderDao.count() == 0

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getOrdersByTabAndSearchText(
        selectedTab: HomeTabs,
        searchText: String,
    ): List<OrderToClient> {
        var dateForQuerys = OffsetDateTime.now().toLocalDate().toString()
        Log.e("GETTT ", searchText + " suspend fun")
        return when (selectedTab) {
            HomeTabs.History -> {
                orderDao.getOrdersByHistoryAndSearchText(dateForQuerys, searchText)
            }
            HomeTabs.Current -> {
                orderDao.getOrdersByCurrentAndSearchText(dateForQuerys, searchText)
            }
        }
    }

}