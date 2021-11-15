package com.example.zeytunpharma

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.zeytunpharma.home.HomeWithSearch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ZeytunPharmaApp(navigateToDetail: (Long) -> Unit) {
    Log.e("ZeytunPharmaApp ", "ENTERED")
    val context = LocalContext.current
    var isOnline by remember { mutableStateOf(checkIfOnline(context)) }

    if (isOnline) {
        HomeWithSearch(navigateToDetail)
    } else {
        OfflineDialog { isOnline = checkIfOnline(context) }
    }
}

@Suppress("DEPRECATION")
private fun checkIfOnline(context: Context): Boolean {
    var cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork) ?: return false
        capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    } else {
        cm.activeNetworkInfo?.isConnectedOrConnecting == true
    }

}

@Composable
fun OfflineDialog(onRetry: () -> Unit) {
    AlertDialog(onDismissRequest = { /*TODO*/ },
        title = { Text(text = stringResource(id = R.string.connection_error_title)) },
        text = { Text(text = stringResource(id = R.string.connection_error_message)) },
        confirmButton = {
            TextButton(onClick = onRetry) {
                Text(stringResource(id = R.string.retry_label))
            }
        })
}