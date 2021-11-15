package com.example.zeytunpharma.home.orderdetail

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.example.zeytunpharma.data.Order
import com.example.zeytunpharma.data.OrderMedicineStore
import com.example.zeytunpharma.data.OrderStore
import com.example.zeytunpharma.data.OrderToMedicine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class MedicineViewModel(
    private val orderStore: OrderStore,
    private val orderMedicineStore: OrderMedicineStore,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    // Holds our view state which the UI collects via [state]
    private val _state = MutableStateFlow(MedicineViewState())
    private val refreshing = MutableStateFlow(false)
    val state: StateFlow<MedicineViewState>
        get() = _state

    private val orderId: Long = savedStateHandle.get<Long>(ORDER_ID_KEY)!!

    init {
        viewModelScope.launch {
            // Combines the latest value from each of the flows, allowing us to generate a
            // view state instance which only contains the latest values.

            val selectedOrder = orderStore.getOrderNameById(orderId)
            val orderMedicineCategory = orderMedicineStore.getMedicinesByOrderId(orderId)

            combine(
                selectedOrder,
                orderMedicineCategory,
            ) { order, orderMedicineCategory ->
                MedicineViewState(
                    selectedOrder = order,
                    orderMedicineCategory = orderMedicineCategory,
                )
            }.collect {

                _state.value = it
            }
        }
    }

    fun orderCompleted() {
        viewModelScope.launch {
            orderStore.updateOrderCompleted(orderId)
        }
    }

    private fun refresh(force: Boolean) {
        viewModelScope.launch {
            runCatching {
                Log.e("MEdicineViewMOdel ", "RUNCATCHING")
                refreshing.value = true

            }
            // TODO: look at result of runCatching and show any errors

            refreshing.value = false
        }
    }

    companion object {
        const val ORDER_ID_KEY = "orderId"

        fun provideFactory(
            orderStore: OrderStore,
            orderMedicineStore: OrderMedicineStore,
            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle? = null,
        ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle,
                ): T {
                    return MedicineViewModel(orderStore, orderMedicineStore, handle) as T
                }
            }
    }

}

data class MedicineViewState(
    val selectedOrder: Order? = null,
    val orderMedicineCategory: List<OrderToMedicine> = emptyList(),
)