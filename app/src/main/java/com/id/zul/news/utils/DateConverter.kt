package com.id.zul.news.utils

import java.text.SimpleDateFormat
import java.util.*

object DateConverter {
    fun formatDate(date: Date): String {
        return SimpleDateFormat("EEEE, dd MMM yyyy", Locale.getDefault()).format(date)
    }
}