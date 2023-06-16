package com.c23_ps162.trade_net.util

import com.c23_ps162.trade_net.util.pattern.Default
import com.c23_ps162.trade_net.util.pattern.Failed
import com.c23_ps162.trade_net.util.pattern.Loading
import com.c23_ps162.trade_net.util.pattern.Success


private data class DefaultImpl<T>(
    private val value: Unit = Unit
) : Default<T>

private data class SuccessImpl<T>(
    override val data: T?
) : Success<T>

private data class FailedImpl<T>(
    override val message: String?
) : Failed<T>

private data class LoadingImpl<T>(
    private val value: Unit = Unit
) : Loading<T>

fun <T> vmDefault(): Default<T> = DefaultImpl()

fun <T> vmSuccess(
    data: T?
): Success<T> = SuccessImpl(
    data = data
)

fun <T> vmFailed(
    message: String?
): Failed<T> = FailedImpl(
    message = message
)

fun <T> vmLoading(): Loading<T> = LoadingImpl()