package com.c23_ps162.trade_net.util

import androidx.lifecycle.ViewModel
import com.c23_ps162.trade_net.util.pattern.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

fun <T> ViewModel.state(default: T): Lazy<State<T>> = lazy {
    object : State<T> {
        private val state = MutableStateFlow(default)

        override fun set(seed: T) {
            ViewModelJob {
                state.emit(seed)
            }
        }

        override val flow: Flow<T>
            get() = state

    }
}