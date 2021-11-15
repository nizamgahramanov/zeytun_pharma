package com.example.zeytunpharma

import android.content.Context
import androidx.room.Room
import com.example.zeytunpharma.data.*
import com.example.zeytunpharma.data.room.TransactionRunner
import com.example.zeytunpharma.data.room.ZeytunPharmaDatabase
import com.rometools.rome.io.SyndFeedInput
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.LoggingEventListener
import java.io.File

object Graph {

    lateinit var okHttpClient: OkHttpClient

    lateinit var database: ZeytunPharmaDatabase
        private set
    private val transactionRunner: TransactionRunner
        get() = database.transactionRunnerDao()

    private val syndFeedInput by lazy { SyndFeedInput() }

    val orderRepository by lazy {
        OrderRepository(
            orderFetcher = orderFetcher,
            orderStore = orderStore,
            medicineStore = medicineStore,
            categoryStore = categoryStore,
            clientStore = clientStore,
            orderMedicineStore = orderMedicineStore,
            userStore = userStore,
            transactionRunner = transactionRunner,
            mainDispatcher = mainDispatcher,
        )
    }

    private val orderFetcher by lazy {
        OrderFetcher(
            okHttpClient = okHttpClient,
            syndFeedInput = syndFeedInput,
            ioDispatcher = ioDispatcher
        )
    }
    val orderStore by lazy {
        OrderStore(
            orderDao = database.ordersDao(),
            transactionRunner = transactionRunner
        )
    }

    val medicineStore by lazy {
        MedicineStore(
            medicineDao = database.medicineDao(),
            transactionRunner = transactionRunner
        )
    }
    val categoryStore by lazy {
        CategoryStore(
            categoryDao = database.categoryDao(),
            transactionRunner = transactionRunner
        )
    }

    val orderMedicineStore by lazy {
        OrderMedicineStore(
            orderMedicineDao = database.orderMedicineDao(),
            transactionRunner = transactionRunner
        )
    }

    val clientStore by lazy {
        ClientStore(
            clientDao = database.clientDao(),
            transactionRunner = transactionRunner
        )
    }

    val userStore by lazy {
        UserStore(
            userDao = database.userDao(),
            transactionRunner = transactionRunner
        )
    }
    private val mainDispatcher: CoroutineDispatcher
        get() = Dispatchers.Main

    private val ioDispatcher: CoroutineDispatcher
        get() = Dispatchers.IO

    fun provide(context: Context) {

        okHttpClient = OkHttpClient.Builder()
            .cache(Cache(File(context.cacheDir, "http_cache"), (20 * 1024 * 1024).toLong()))
            .apply {
                if (BuildConfig.DEBUG) eventListenerFactory(LoggingEventListener.Factory())
            }
            .build()

        database = Room.databaseBuilder(context, ZeytunPharmaDatabase::class.java, "data.db")
            // This is not recommended for normal apps, but the goal of this sample isn't to
            // showcase all of Room.
            .fallbackToDestructiveMigration()
            .build()

    }
}