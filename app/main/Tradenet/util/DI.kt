package com.c23_ps162.trade_net.util

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val utilModule = module {
    single { Preference(androidApplication()) }
    single { DummyBackend(androidApplication()) }
}