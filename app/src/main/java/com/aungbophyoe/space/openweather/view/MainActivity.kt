package com.aungbophyoe.space.openweather.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import com.aungbophyoe.space.openweather.R
import com.aungbophyoe.space.openweather.utils.NetworkConnectivityChecker
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    var snackbar: Snackbar? = null
    private val mainConstraintLayout : ConstraintLayout by lazy {
        findViewById(R.id.mainConstraintLayout)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setInternetConnectivityObserver()
    }

    override fun onResume() {
        super.onResume()
        NetworkConnectivityChecker.checkForConnection()
    }

    private fun setInternetConnectivityObserver() {
        NetworkConnectivityChecker.observe(this, liveDataObserver)
    }

    private val liveDataObserver: Observer<Boolean> = Observer { isConnected ->
        if (!isConnected) {
            //Can use your own logic later -- Using snackbar as default. Build your own listener to create custom view
            mainConstraintLayout?.let {
                snackbar = Snackbar.make(it, "No Internet Connection", Snackbar.LENGTH_LONG)
                snackbar?.show()
            }
        } else {
            snackbar?.dismiss()
        }
    }
}