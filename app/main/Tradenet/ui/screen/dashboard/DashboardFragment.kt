package com.c23_ps162.trade_net.ui.screen.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.c23_ps162.trade_net.R
import com.c23_ps162.trade_net.databinding.FragmentDashboardBinding
import com.c23_ps162.trade_net.ui.screen.home.HomeFragment
import com.c23_ps162.trade_net.ui.screen.profile.ProfileFragment
import com.c23_ps162.trade_net.ui.screen.search.SearchFragment
import com.c23_ps162.trade_net.util.InViewScope
import com.c23_ps162.trade_net.util.Preference
import com.c23_ps162.trade_net.util.collect
import com.c23_ps162.trade_net.util.pattern.Bootstrap
import com.c23_ps162.trade_net.util.showExitConfirmationDialog
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment(), Bootstrap {

    private lateinit var binding: FragmentDashboardBinding
    private val vm: DashboardViewModel by viewModel()
    private val preference by inject<Preference>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(
            inflater,
            container,
            false
        )

        initNavigation()
        initState()

        return binding.root
    }

    override fun initNavigation() {
        /** checking interest list availability **/
        InViewScope {
            val interests = preference.getUserProfile()?.userInterest ?: listOf()

            if (interests.isEmpty()) {
                gotoSelectInterest()
            }
        }
    }

    override fun initState() {
        setupBackPress()
        setupBottomNavigation()
        setupFragmentContainer()
    }

    private fun setupBackPress() {
        activity?.onBackPressedDispatcher
            ?.addCallback(viewLifecycleOwner) {
                showExitConfirmationDialog()
            }
    }

    private fun setupBottomNavigation() {
        val btmNav = binding.btmNav

        btmNav.setOnItemSelectedListener {
            val menuOrder = when(it.itemId) {
                R.id.menu_home -> 0
                R.id.menu_search -> 1
                else -> 2
            }

            vm.selectedBottomNavMenu.set(menuOrder)

            true
        }
    }

    private fun setupFragmentContainer() {

        /** set first fragment to HomeFragment **/
        showHome()

        collect(
            vm.selectedBottomNavMenu.flow
        ) {
            when(it) {
                0 -> showHome()
                1 -> showSearch()
                2 -> showProfile()
            }
        }
    }

    private fun showHome() {
        childFragmentManager.beginTransaction()
            .replace(binding.fragment.id, HomeFragment())
            .commit()
    }

    private fun showSearch() {
        childFragmentManager.beginTransaction()
            .replace(binding.fragment.id, SearchFragment())
            .commit()
    }

    private fun showProfile() {
        childFragmentManager.beginTransaction()
            .replace(binding.fragment.id, ProfileFragment())
            .commit()
    }

    private fun gotoSelectInterest() {
        val action = DashboardFragmentDirections.actionDashboardFragmentToInterestFragment()
        findNavController()
            .navigate(action)
    }
}