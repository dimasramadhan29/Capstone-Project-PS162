package com.c23_ps162.trade_net.util

import androidx.lifecycle.ViewModel
import arrow.core.Either
import com.c23_ps162.trade_net.util.pattern.Failed
import com.c23_ps162.trade_net.util.pattern.Loading
import com.c23_ps162.trade_net.util.pattern.Provider
import com.c23_ps162.trade_net.util.pattern.Success
import com.c23_ps162.trade_net.util.pattern.VM
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

fun <P, T> ViewModel.provider(
    operator: suspend (P) -> Either<ErrorMessage, T?>,
): Lazy<Provider<P, T>> {
    return lazy {
        object : Provider<P, T> {
            override val operator: suspend (P) -> Either<ErrorMessage, T?> = operator

            private val _flow = MutableStateFlow<VM<T>>(vmDefault())

            override val flow: Flow<VM<T>> = _flow

            override val success: Flow<Pair<Boolean, T?>> = flow.map {
                (it is Success<T>) to
                        if (it is Success<T>) it.data
                        else null
            }

            override val failed: Flow<Pair<Boolean, ErrorMessage>> = flow.map {
                (it is Failed<T>) to
                        if (it is Failed<T>)
                            it.message ?: "Unknown Error"
                        else ""
            }

            override val loading: Flow<Boolean> = flow.map {
                (it is Loading<T>)
            }

            private var updateJob: Job? = null
            override suspend fun update(pld: P) {
                updateJob?.cancel()

                _flow.emit(vmLoading())

                updateJob = ViewModelJob {
                    val result = operator.invoke(pld)

                    result
                        .map {
                            _flow.emit(
                                vmSuccess(it)
                            )
                        }
                        .mapLeft {
                            _flow.emit(
                                vmFailed(it)
                            )
                        }
                }
            }

        }
    }
}