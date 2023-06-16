package com.c23_ps162.trade_net.data

import com.c23_ps162.trade_net.data.repository.AuthRepository
import com.c23_ps162.trade_net.data.repository.ContentRepository
import com.c23_ps162.trade_net.data.repository.ProfileRepository
import org.koin.dsl.module

val dataModule = module {
    single {
        AuthRepository(get())
    }
    single {
        ContentRepository(get())
    }
    single {
        ProfileRepository(get())
    }
}