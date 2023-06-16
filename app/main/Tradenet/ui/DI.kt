package com.c23_ps162.trade_net.ui

import com.c23_ps162.trade_net.ui.screen.dashboard.DashboardViewModel
import com.c23_ps162.trade_net.ui.screen.interest.InterestViewModel
import com.c23_ps162.trade_net.ui.screen.login.LoginViewModel
import com.c23_ps162.trade_net.ui.screen.makepost.MakePostFragmentViewModel
import com.c23_ps162.trade_net.ui.screen.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel {
        LoginViewModel(
            get()
        )
    }

    viewModel {
        RegisterViewModel(
            get()
        )
    }

    viewModel {
        InterestViewModel(
            get(),
            get()
        )
    }

    viewModel {
        DashboardViewModel()
    }

    viewModel {
        MakePostFragmentViewModel()
    }
}