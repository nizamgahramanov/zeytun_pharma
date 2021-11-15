package com.example.zeytunpharma

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.zeytunpharma.data.UserStore
import com.example.zeytunpharma.home.orderdetail.MedicineViewModel
import com.example.zeytunpharma.home.orderdetail.MedicineViewModel.Companion.ORDER_ID_KEY
import com.example.zeytunpharma.home.orderdetail.OrderDetail
import com.example.zeytunpharma.signin.SingInScreen


object MainDestinations {
    const val HOME_ROUTE = "home"
    const val SIGNIN_ROUTE = "signin"
    const val ORDER_ROUTE = "order"
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ZeytunPharmaNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = MainDestinations.SIGNIN_ROUTE,
) {
    Log.e("ZeytunPharmaNavGraph", " Entered")

    val userStore: UserStore = Graph.userStore
    val actions = remember(navController) { MainActions(navController, userStore) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainDestinations.SIGNIN_ROUTE) { backStackEntry ->
            Log.e("Composable ", backStackEntry.toString())
            val email = navController.previousBackStackEntry?.arguments?.getString("email")
            Log.e("Email ", email.toString())
            SingInScreen(navigateToHome = actions.navigateToHomeAfterSubmit)

        }
        composable(
            route = MainDestinations.HOME_ROUTE) {
            ZeytunPharmaApp(
                navigateToDetail = actions.navigateToDetail,
            )
        }

        composable(
            route = "${MainDestinations.ORDER_ROUTE}/{$ORDER_ID_KEY}",
            arguments = listOf(navArgument(ORDER_ID_KEY) { type = NavType.LongType })
        ) { backStackEntry ->

            val medicineViewModel: MedicineViewModel = viewModel(
                factory = MedicineViewModel.provideFactory(
                    orderStore = Graph.orderStore,
                    orderMedicineStore = Graph.orderMedicineStore,
                    owner = backStackEntry,
                    defaultArgs = backStackEntry.arguments
                )
            )
            OrderDetail(
                medicineViewModel = medicineViewModel,
                onBack = actions.upPress,
                onSubmitPressed = actions.navigateToHomeAfterSubmit
            )
        }
    }
}

class MainActions(navController: NavHostController, userStore: UserStore) {


    val navigateToDetail: (Long) -> Unit = { orderId: Long ->
        Log.e("ZeytunPharmaNavGraph", orderId.toString())
        navController.navigate("${MainDestinations.ORDER_ROUTE}/$orderId")
    }

    val navigateToHomeAfterSubmit: () -> Unit = {
        Log.e("Navigate", "hm.e")
        navController.navigate("${MainDestinations.HOME_ROUTE}")
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}