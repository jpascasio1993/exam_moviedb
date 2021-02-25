package com.exam.moviedb.utils.gson

import android.util.Log
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

class StringNullTypeAdapter: TypeAdapter<String>() {
    override fun write(out: JsonWriter?, value: String?) {
//        if(value == null) {
//            out!!.nullValue()
//            return
//        }
        var temp = value
        if(value == null) {
            out!!.nullValue()
            temp = ""
        }
        out!!.value(temp)
    }

    override fun read(`in`: JsonReader?): String {
        val temp = `in`!!.peek()
        Log.i(this.javaClass.canonicalName, "read: $temp")
        if (temp == JsonToken.NULL) {
            `in`.nextNull()
            return ""
        }
        val temp2 = `in`.nextString()
        Log.i(this.javaClass.canonicalName, "read2: $temp2")
        return temp2
    }
}