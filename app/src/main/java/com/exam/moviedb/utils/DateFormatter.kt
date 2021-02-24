package com.exam.moviedb.utils

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

fun String.dateFormat(inputFormat: String = "yyyy-MM-dd", outputFormat: String = "MMM yyyy"): String {
        val dateFormatIn = SimpleDateFormat(inputFormat, Locale.getDefault())
        val dateFormatOut = SimpleDateFormat(outputFormat, Locale.getDefault())

        if(this.isEmpty()) return this

        return try {
            val parsed = dateFormatIn.parse(this)
            dateFormatOut.format(parsed)
        }catch(e: Exception) {
            return this
        }
    }