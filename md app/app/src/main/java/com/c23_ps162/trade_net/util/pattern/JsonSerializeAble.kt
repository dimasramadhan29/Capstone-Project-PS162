package com.c23_ps162.trade_net.util.pattern

import com.google.gson.Gson

interface JsonSerializeAble {
    fun toJsonString(): String {
        return Gson().toJson(this)
    }
}