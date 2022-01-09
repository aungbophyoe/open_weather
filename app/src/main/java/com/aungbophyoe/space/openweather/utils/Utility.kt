package com.aungbophyoe.space.openweather.utils

import java.text.SimpleDateFormat
import java.util.*

open class Utility {
    fun getDateTime(s: String): String? {
        return try {
            val sdf = SimpleDateFormat("dd-MM-yyyy")
            val netDate = Date(s.toLong() * 1000)
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
    }
}