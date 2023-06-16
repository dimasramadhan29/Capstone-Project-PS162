package com.c23_ps162.trade_net.util.pattern

import arrow.core.Either
import com.c23_ps162.trade_net.util.ErrorMessage
import kotlinx.coroutines.flow.Flow

interface Provider<P, T> {
    val operator: suspend (P) -> Either<ErrorMessage, T?>
    val flow: Flow<VM<T>>
    val success: Flow<Pair<Boolean, T?>>
    val failed: Flow<Pair<Boolean, ErrorMessage>>
    val loading: Flow<Boolean>

    suspend fun update(pld: P)
}