package com.pavelb.mishlohatest.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale




fun getDateString(startDate: StartDate): String {
        val calendar = Calendar.getInstance()
        when (startDate) {
            StartDate.DAY -> calendar.add(Calendar.DAY_OF_MONTH, -1)
            StartDate.WEEK -> calendar.add(Calendar.WEEK_OF_YEAR, -1)
            StartDate.MONTH -> calendar.add(Calendar.MONTH, -1)
        }
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

fun makeDateQueryString(date: String): String {
    return "created:>$date"
}
