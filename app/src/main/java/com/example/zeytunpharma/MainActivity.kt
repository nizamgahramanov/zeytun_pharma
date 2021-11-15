package com.example.zeytunpharma

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.zeytunpharma.ui.theme.ZeytunPharmaTheme
import com.google.accompanist.insets.ProvideWindowInsets

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        Log.e("MainACtivity", "Entered")
        setContent {
            ZeytunPharmaTheme {
                ProvideWindowInsets {
                    val navController = rememberNavController()
                    ZeytunPharmaNavGraph(
                        navController = navController
                    )
                }
            }
        }
    }
}
