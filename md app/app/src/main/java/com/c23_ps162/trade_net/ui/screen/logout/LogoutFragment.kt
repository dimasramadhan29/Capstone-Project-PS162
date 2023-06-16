package com.c23_ps162.trade_net.ui.screen.logout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.c23_ps162.trade_net.databinding.FragmentLoginBinding
import com.c23_ps162.trade_net.databinding.FragmentLogoutBinding
import com.c23_ps162.trade_net.ui.screen.login.LoginViewModel
import com.c23_ps162.trade_net.util.Preference
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LogoutFragment : Fragment() {

    private lateinit var binding: FragmentLogoutBinding
    private val state: LoginViewModel by viewModel()
    private val preference by inject<Preference>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogoutBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }
}