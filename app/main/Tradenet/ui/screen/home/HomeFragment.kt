package com.c23_ps162.trade_net.ui.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.c23_ps162.trade_net.databinding.FragmentHomeBinding
import com.c23_ps162.trade_net.util.Preference
import com.c23_ps162.trade_net.util.pattern.Bootstrap
import org.koin.android.ext.android.inject

class HomeFragment: Fragment(), Bootstrap {
    private lateinit var binding: FragmentHomeBinding
    private val preference by inject<Preference>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(
            inflater,
            container,
            false
        )

        setupRV()
        return binding.root
    }

    private fun setupRV() {
        val adapter = PostItemAdapter(
            {},
            {},
            {}
        )
        binding.rv.adapter = adapter
    }
}