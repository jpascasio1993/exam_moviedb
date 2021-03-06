package com.exam.moviedb.db.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListIntDataConverter{

    @TypeConverter
    fun fromListToString(list: List<Int>): String {
        if(list.isEmpty()) return ""
        return list.joinToString(separator = ",")
    }

    @TypeConverter
    fun stringToList(str: String): List<Int> {
        if(str.isEmpty()) return emptyList()
        return str.split(",").map { it.toInt() }
    }
}