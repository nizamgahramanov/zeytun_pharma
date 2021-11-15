package com.example.zeytunpharma.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zeytunpharma.Graph
import com.example.zeytunpharma.data.User
import com.example.zeytunpharma.data.UserStore
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {

    private val userStore: UserStore = Graph.userStore
    private val refreshing = MutableStateFlow(false)
    private val _state = MutableStateFlow(SignInViewState())
    val state: StateFlow<SignInViewState>
        get() = _state

    init {
        viewModelScope.launch {
            // Combines the latest value from each of the flows, allowing us to generate a
            // view state instance which only contains the latest values.
            Log.e("INIT METODU ", "HomVIEWMODEL ENTERED")

            combine(
                userStore.getUserNameByUserEmail("nizam@gmail.com"),
                refreshing,
            ) { user, refreshing ->
                SignInViewState(
                    user = user,
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

        refresh(force = false)
    }

    private fun refresh(force: Boolean) {
        viewModelScope.launch {
            Log.e("REFRESHHHHHH ", force.toString())
            runCatching {
                refreshing.value = true
                Graph.orderRepository.updateOrders(force)
            }
            // TODO: look at result of runCatching and show any errors

            refreshing.value = false
        }
    }
}

data class SignInViewState(
    val user: User? = null,
    val refreshing: Boolean = false,
    val errorMessage: String? = null,
)