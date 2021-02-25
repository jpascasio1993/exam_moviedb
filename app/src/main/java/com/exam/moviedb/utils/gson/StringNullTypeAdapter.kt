package com.exam.moviedb.utils.gson

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter

class StringNullTypeAdapter: TypeAdapter<String>() {
    override fun write(out: JsonWriter?, value: String?) {
        if(value == null) {
            out!!.nullValue()
            return
        }
        out!!.value(value)
    }

    override fun read(`in`: JsonReader?): String {
        if (`in`!!.peek() == JsonToken.NULL) {
            `in`.nextNull();
            return ""
        }
        return `in`.nextString()
    }
}