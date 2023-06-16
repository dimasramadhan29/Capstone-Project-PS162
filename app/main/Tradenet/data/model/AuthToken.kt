package com.c23_ps162.trade_net.data.model

import com.google.gson.annotations.SerializedName

data class AuthToken(
    @field:SerializedName("token")
    val token: String
)
