package com.c23_ps162.trade_net.data.model

import com.google.gson.annotations.SerializedName

data class ResponseWrapper<T>(

    @field:SerializedName("data")
    val data: T? = null,

    @field:SerializedName("success")
    val success: Boolean? = null,

    @field:SerializedName("errorCode")
    val errorCode: String? = null,

    @field:SerializedName("error")
    val error: String? = null
)
