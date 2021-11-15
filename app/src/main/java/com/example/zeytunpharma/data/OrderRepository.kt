package com.example.zeytunpharma.data

import android.util.Log
import com.example.zeytunpharma.data.room.TransactionRunner
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class OrderRepository(
    private val orderFetcher: OrderFetcher,
    private val orderStore: OrderStore,
    private val transactionRunner: TransactionRunner,
    private val medicineStore: MedicineStore,
    private val categoryStore: CategoryStore,
    private val orderMedicineStore: OrderMedicineStore,
    private val clientStore: ClientStore,
    private val userStore: UserStore,
    mainDispatcher: CoroutineDispatcher,
) {

    private var refreshingJob: Job? = null

    private val scope = CoroutineScope(mainDispatcher)

    suspend fun updateOrders(force: Boolean) {
        Log.e("ORDERREPO ", "Entered")
        if (refreshingJob?.isActive == true) {
            Log.e("updateOrdersJOB", refreshingJob?.isActive.toString())
            refreshingJob?.join()
        } else if (force || orderStore.isEmpty()) {
            Log.e("updateOrders ", orderStore.isEmpty().toString())
            refreshingJob = scope.launch {
                orderFetcher(SampleOrderMedicines, SampleOrders, SampleCategories, SampleMedicines,
                    SampleClients, SampleUsers)
                    .filter { it is OrderRssResponse.Success }
                    .map { it as OrderRssResponse.Success }
                    .collect { (orderMedicines, order, categories, medicines, clients, users) ->
                        transactionRunner {
                            clients.forEach { client ->
                                val clientId = clientStore.addClient(client)
                            }

                            val orderId = orderStore.addOrder(order)
                            Log.e("ORDER ID ", orderId.toString())
                            categories.forEach { category ->
                                val categoryId = categoryStore.addCategory(category)
                            }
                            medicines.forEach { medicine ->
                                val medicineId = medicineStore.addMedicine(medicine)
//                                Log.e("medicineId ID ", medicineId.toString())
                                orderMedicineStore.addOrderMedicine(orderId, medicineId)
                            }
                            users.forEach { user ->
                                userStore.addUser(user)
                            }

                        }
                    }

            }
            Log.e("REFRESHING JOB ", refreshingJob.toString())

        }
    }

//    suspend fun insertOrders(order: Order)=
//        orderDao.insertOrder(order)
//    suspend fun updateOrder(order: Order)=
//        orderDao.updateOrder(order)
//    suspend fun deleteOrder(order: Order)=
//        orderDao.deleteOrder(order)
//    fun getAllOrders():LiveData<List<Order>> = orderDao.getAllOrders()
}