package com.c23_ps162.trade_net.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun Fragment.InViewScope(block: suspend () -> Unit): Job {
    return viewLifecycleOwner.lifecycleScope
        .launch { block.invoke() }
}

fun Fragment.IOJob(block: suspend () -> Unit): Job {
    return CoroutineScope(Dispatchers.IO).launch {
        block.invoke()
    }
}

fun ViewModel.ViewModelJob(block: suspend () -> Unit): Job {
    return viewModelScope.launch {
        block.invoke()
    }
}