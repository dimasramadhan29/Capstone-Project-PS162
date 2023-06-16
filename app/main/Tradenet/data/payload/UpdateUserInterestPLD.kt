package com.c23_ps162.trade_net.data.payload

import com.c23_ps162.trade_net.data.model.Interest

data class UpdateUserInterestPLD(
    val interest: List<Interest>
)