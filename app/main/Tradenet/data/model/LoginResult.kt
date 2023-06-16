package com.c23_ps162.trade_net.data.model

import com.google.gson.annotations.SerializedName

data class LoginResult(
    @field:SerializedName("token")
    val token: AuthToken?,
    @field:SerializedName("userProfile")
    val userProfile: UserProfile?
)
