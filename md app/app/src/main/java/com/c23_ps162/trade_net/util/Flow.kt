package com.c23_ps162.trade_net.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

fun <T> Fragment.collect(
    flow: Flow<T>,
    block: suspend (T) -> Unit
): Job {
    return InViewScope {
        flow.collect {
            block.invoke(it)
        }
    }
}

fun <T> ViewModel.collect(
    flow: Flow<T>,
    block: suspend (T) -> Unit
): Job {
    return ViewModelJob {
        flow.collect {
            block.invoke(it)
        }
    }
}