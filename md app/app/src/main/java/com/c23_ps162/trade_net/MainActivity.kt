package com.c23_ps162.trade_net

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.c23_ps162.trade_net.util.pattern.Bootstrap

class MainActivity : AppCompatActivity(), Bootstrap {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initState()
    }

    override fun initState() {
        initNavigation()
    }

    override fun initNavigation() {

    }

}