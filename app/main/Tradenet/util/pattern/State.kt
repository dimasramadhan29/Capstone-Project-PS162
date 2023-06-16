package com.c23_ps162.trade_net.util.pattern

import kotlinx.coroutines.flow.Flow

interface State<T> {
    fun set(seed: T)
    val flow: Flow<T>
}