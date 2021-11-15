package com.example.zeytunpharma.data

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zeytunpharma.Graph.orderRepository
import com.example.zeytunpharma.Graph.orderStore
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class HomeViewModel : ViewModel() {
    // Holds our currently selected home tabs
    private val selectedTab = MutableStateFlow(HomeTabs.Current)

    // Holds the currently available home tabs
    private val tabs = MutableStateFlow(HomeTabs.values().asList())
    private val refreshing = MutableStateFlow(false)
    private val _state = MutableStateFlow(HomeViewState())
    val state: StateFlow<HomeViewState>
        get() = _state

    init {
        viewModelScope.launch {
            // Combines the latest value from each of the flows, allowing us to generate a
            // view state instance which only contains the latest values.
            Log.e("INIT METODU ", "HomVIEWMODEL ENTERED")

            combine(
                tabs,
                selectedTab,
                orderStore.getOrdersByTab(selectedTab.value, ""),
                refreshing,
            ) { tabs, selectedTab, orderToClients, refreshing ->
                HomeViewState(
                    homeTabs = tabs,
                    selectedHomeTab = selectedTab,
                    orderToClients = orderToClients,
                    refreshing = refreshing,
                    errorMessage = null,
                )

            }.catch { throwable ->
                throw  throwable
            }.collect {
                Log.e("viewModelScope ", it.toString())
                _state.value = it
            }
        }

//        refresh(force = false)
    }

    private fun refresh(force: Boolean) {
        viewModelScope.launch {
            Log.e("REFRESHHHHHH ", force.toString())
            runCatching {
                refreshing.value = true
                orderRepository.updateOrders(force)
            }
            // TODO: look at result of runCatching and show any errors

            refreshing.value = false
        }
    }

    fun onHomeTabSelected(tab: HomeTabs) {
        selectedTab.value = tab
        Log.e("ONHOMETABSELECTED ", tab.toString())
        viewModelScope.launch {
            // Combines the latest value from each of the flows, allowing us to generate a
            // view state instance which only contains the latest values.
            Log.e("INIT METODU ", "HomVIEWMODEL ENTERED")

            combine(
                tabs,
                selectedTab,
                orderStore.getOrdersByTab(selectedTab.value, ""),
                refreshing,
            ) { tabs, selectedTab, orderToClients, refreshing ->
                HomeViewState(
                    homeTabs = tabs,
                    selectedHomeTab = selectedTab,
                    orderToClients = orderToClients,
                    refreshing = refreshing,
                    errorMessage = null,
                )

            }.catch { throwable ->
                throw  throwable
            }.collect {
                Log.e("viewModelScope ", it.toString())
                _state.value = it
            }
        }
    }
}

enum class HomeTabs {
    Current, History
}

data class HomeViewState(
    val orderToClients: List<OrderToClient> = emptyList(),
    val homeTabs: List<HomeTabs> = emptyList(),
    val selectedHomeTab: HomeTabs = HomeTabs.Current,
    val refreshing: Boolean = false,
    val errorMessage: String? = null,
)