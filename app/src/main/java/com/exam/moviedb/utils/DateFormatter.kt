package com.exam.moviedb.utils

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {
    fun format(inputFormat: String = "yyyy-MM-dd", outputFormat: String = "MMM yyyy", input: String): String {
        val dateFormatIn = SimpleDateFormat(inputFormat, Locale.getDefault())
        val dateFormatOut = SimpleDateFormat(outputFormat, Locale.getDefault())

        if(input.isEmpty()) return input

        return try {
            val parsed = dateFormatIn.parse(input)
            dateFormatOut.format(parsed)
        }catch(e: Exception) {
            return input
        }
    }
}