package com.c23_ps162.trade_net.data.payload

data class RegistrationPLD(
    val userName: String,
    val userEmail: String,
    val password: String,
    val phoneNumber: String
)