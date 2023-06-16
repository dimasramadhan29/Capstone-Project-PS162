package com.c23_ps162.trade_net.ui.screen.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.c23_ps162.trade_net.databinding.FragmentSplashBinding
import com.c23_ps162.trade_net.util.InViewScope
import com.c23_ps162.trade_net.util.Preference
import com.c23_ps162.trade_net.util.pattern.Bootstrap
import kotlinx.coroutines.delay
import org.koin.android.ext.android.inject

class SplashFragment : Fragment(), Bootstrap {

    private lateinit var binding: FragmentSplashBinding
    private val preference by inject<Preference>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSplashBinding.inflate(
            inflater,
            container,
            false
        )

        initNavigation()
        initState()
        return binding.root
    }

    override fun initNavigation() {
        InViewScope {
            delay(2000)

            val token = preference.getAuthToken()
            if (token.isNullOrBlank())
                gotoLogin()
            else
                gotoHome()
        }
    }

    override fun initState() {

    }

    private fun gotoHome() {
        val action = SplashFragmentDirections.actionSplashFragmentToDashboardFragment()
        findNavController().navigate(action)
    }

    private fun gotoLogin() {
        val action = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
        findNavController().navigate(action)
    }
}