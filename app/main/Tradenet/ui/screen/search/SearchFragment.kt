package com.c23_ps162.trade_net.ui.screen.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.c23_ps162.trade_net.databinding.FragmentProfileBinding
import com.c23_ps162.trade_net.databinding.FragmentSearchBinding
import com.c23_ps162.trade_net.util.Preference
import org.koin.android.ext.android.inject

class SearchFragment: Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val preference by inject<Preference>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(
            inflater,
            container,
            false
        )
        setupRV()
        return binding.root
    }

    private fun setupRV() {
        val adapter = SearchItemAdapter{}
        binding.rv.adapter = adapter
    }
}