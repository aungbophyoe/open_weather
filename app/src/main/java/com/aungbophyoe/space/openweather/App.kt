package com.aungbophyoe.space.openweather

import android.app.Application
import com.aungbophyoe.space.openweather.utils.NetworkConnectivityChecker
import dagger.hilt.android.HiltAndroidApp

/*
*  create by aungbophyoe
*  on 7/1/2022
* */

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initNetworkConnectivityChecker()
    }

    private fun initNetworkConnectivityChecker() {
        NetworkConnectivityChecker.init(this.applicationContext)
    }
}