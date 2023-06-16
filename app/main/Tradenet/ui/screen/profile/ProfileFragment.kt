package com.c23_ps162.trade_net.ui.screen.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.c23_ps162.trade_net.databinding.FragmentHomeBinding
import com.c23_ps162.trade_net.databinding.FragmentProfileBinding
import com.c23_ps162.trade_net.ui.screen.dashboard.DashboardFragmentDirections
import com.c23_ps162.trade_net.util.Preference
import com.c23_ps162.trade_net.util.pattern.Bootstrap
import org.koin.android.ext.android.inject

class ProfileFragment : Fragment(), Bootstrap {
    private lateinit var binding: FragmentProfileBinding
    private val preference by inject<Preference>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(
            inflater,
            container,
            false
        )

        initState()
        return binding.root
    }

    override fun initState() {
        initFAB()
    }

    private fun initFAB() {
        binding.fab.setOnClickListener {
            val action = DashboardFragmentDirections.actionDashboardFragmentToMakePostFragment()
            this.parentFragment
                ?.findNavController()
                ?.navigate(action)
        }
    }
}