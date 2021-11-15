package com.example.zeytunpharma

import android.app.Application
import android.util.Log

class ZeytunPharmaApplication : Application() {
    override fun onCreate() {
        Log.e("ZeytunPharmaApplication", "ENtered")
        super.onCreate()
        Graph.provide(this)
    }
}