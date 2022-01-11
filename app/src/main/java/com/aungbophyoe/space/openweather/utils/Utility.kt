package com.aungbophyoe.space.openweather.utils

import java.text.SimpleDateFormat
import java.util.*
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


object Utility {
    fun getDateTime(s: String): String? {
        return try {
            val sdf = SimpleDateFormat("EEE, MMM dd, yyyy HH:mm")
            val netDate = Date(s.toLong() * 1000)
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
    }

    fun hideSoftKeyboard(activity: Activity) {
        val edittext: View? = activity.currentFocus
        if (edittext != null) {
            val imm: InputMethodManager =
                (activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)!!
            imm.hideSoftInputFromWindow(edittext.getWindowToken(), 0)
        }
    }

    fun getDay(s:String) : String?{
        return try {
            val sdf = SimpleDateFormat("EEE, MMM dd")
            val netDate = Date(s.toLong() * 1000)
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
    }

}