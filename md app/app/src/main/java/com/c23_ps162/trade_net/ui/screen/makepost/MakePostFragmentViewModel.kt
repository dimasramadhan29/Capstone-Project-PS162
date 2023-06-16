package com.c23_ps162.trade_net.ui.screen.makepost

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.c23_ps162.trade_net.util.state

class MakePostFragmentViewModel: ViewModel() {
    val imageUri by state<Uri?>(null)
}