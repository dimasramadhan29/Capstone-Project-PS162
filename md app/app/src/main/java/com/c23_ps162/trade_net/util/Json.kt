package com.c23_ps162.trade_net.util

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

inline fun <reified T> String.toObject(): T? {
    return try {
        val gson = Gson()
        gson.fromJson(this, T::class.java)
    } catch (e: JsonSyntaxException) {
        e.printStackTrace()
        null
    }
}