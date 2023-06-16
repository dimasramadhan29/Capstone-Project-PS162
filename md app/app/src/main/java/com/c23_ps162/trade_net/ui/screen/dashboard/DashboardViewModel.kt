package com.c23_ps162.trade_net.ui.screen.dashboard

import androidx.lifecycle.ViewModel
import com.c23_ps162.trade_net.util.state

class DashboardViewModel: ViewModel() {

    val selectedBottomNavMenu by state(0)
}