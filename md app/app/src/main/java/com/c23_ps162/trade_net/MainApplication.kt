package com.c23_ps162.trade_net

import android.app.Application
import com.c23_ps162.trade_net.data.dataModule
import com.c23_ps162.trade_net.ui.uiModule
import com.c23_ps162.trade_net.util.utilModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class MainApplication : Application() {

    private val koinModules = arrayListOf(
        uiModule,
        dataModule,
        utilModule
    )

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(koinModules)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}