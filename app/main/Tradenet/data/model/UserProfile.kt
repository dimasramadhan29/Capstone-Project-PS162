package com.c23_ps162.trade_net.data.model

import com.google.gson.annotations.SerializedName
import com.c23_ps162.trade_net.util.ID
import com.c23_ps162.trade_net.util.pattern.JsonSerializeAble

data class UserProfile(
    @field:SerializedName("userId")
    val userId: String? = null,
    @field:SerializedName("userName")
    val userName: String? = null,
    @field:SerializedName("userEmail")
    val userEmail: String? = null,
    @field:SerializedName("userPhone")
    val userPhone: String? = null,
    @field:SerializedName("userInterest")
    val userInterest: List<ID?>? = null
): JsonSerializeAble
