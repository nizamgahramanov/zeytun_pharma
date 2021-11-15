package com.example.zeytunpharma.data

import com.rometools.rome.io.SyndFeedInput
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class OrderFetcher(
    private val okHttpClient: OkHttpClient,
    private val syndFeedInput: SyndFeedInput,
    private val ioDispatcher: CoroutineDispatcher,
) {
    private val cacheControl by lazy {
        CacheControl.Builder().maxStale(8, TimeUnit.HOURS).build()
    }

    operator fun invoke(
        orderMedicines: List<OrderMedicine>,
        orders: List<Order>,
        categories: List<Category>,
        medicines: List<Medicine>,
        clients: List<Client>,
        users: List<User>,
    ): Flow<OrderRssResponse> {
        var x = orders.asFlow().flatMapConcat { order ->
            flow {
                emit(OrderRssResponse.Success(orderMedicines,
                    order,
                    categories,
                    medicines,
                    clients,
                    users))
            }

        }
        return x

    }

}

sealed class OrderRssResponse {
    data class Error(
        val throwable: Throwable?,
    ) : OrderRssResponse()

    data class Success(
        val orderMedicines: List<OrderMedicine>,
        val order: Order,
        val categories: List<Category>,
        val medicines: List<Medicine>,
        val clients: List<Client>,
        val users: List<User>,
    ) : OrderRssResponse()
}
