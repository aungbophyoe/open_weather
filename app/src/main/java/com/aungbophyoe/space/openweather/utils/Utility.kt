package com.aungbophyoe.space.openweather.utils

import java.text.SimpleDateFormat
import java.util.*

object Utility {
    fun getDateTime(s: String): String? {
        return try {
            val sdf = SimpleDateFormat("MMM dd, yyyy HH:mm")
            val netDate = Date(s.toLong() * 1000)
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
    }
}