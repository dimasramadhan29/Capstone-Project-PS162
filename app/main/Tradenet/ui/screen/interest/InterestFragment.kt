package com.c23_ps162.trade_net.ui.screen.interest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.c23_ps162.trade_net.data.payload.GetUserProfilePLD
import com.c23_ps162.trade_net.data.payload.UpdateUserInterestPLD
import com.c23_ps162.trade_net.databinding.FragmentInterestBinding
import com.c23_ps162.trade_net.util.InViewScope
import com.c23_ps162.trade_net.util.Preference
import com.c23_ps162.trade_net.util.collect
import com.c23_ps162.trade_net.util.pattern.Bootstrap
import com.c23_ps162.trade_net.util.showExitConfirmationDialog
import kotlinx.coroutines.flow.first
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class InterestFragment : Fragment(), Bootstrap {

    private lateinit var binding: FragmentInterestBinding
    private val vm: InterestViewModel by viewModel()
    private val pref: Preference by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentInterestBinding.inflate(
            inflater,
            container,
            false
        )

        initState()
        return binding.root
    }

    override fun initState() {
        setupInterestList()
        setupBackPress()
    }

    private fun setupInterestList() {

        val adapter = InterestListAdapter { item ->
            InViewScope {
                val currentList = vm.selectedInterests.flow.first()

                /** selecting **/

                /** selecting **/
                /** selecting **/
                /** selecting **/
                val isSelecting = !item.selected

                /** exclude similar / deselect **/

                /** exclude similar / deselect **/
                /** exclude similar / deselect **/
                /** exclude similar / deselect **/
                var finalList = currentList.filter {
                    it.parent.id != item.parent.id
                }

                if (isSelecting)
                    finalList = finalList.plus(item)

                vm.selectedInterests.set(finalList)

            }
        }
        binding.rv.adapter = adapter

        collect(vm.interestListDisplay) {
            adapter.submitList(it)
        }

        /** button **/
        binding.buttonSubmit.setOnClickListener {
            submitSelections()
        }

        collect(
            vm.submitSelectionProvider.success
        ) {
            if (it.first)
                updateLocalUserProfile()
        }

        collect(
            vm.userProfileProvider.success
        ) {
            if (it.first) {
                it.second?.apply {
                    pref.saveUserProfile(this)
                }

                findNavController().popBackStack()
            }
        }
    }

    private fun setupBackPress() {
        activity?.onBackPressedDispatcher
            ?.addCallback(viewLifecycleOwner) {
                showExitConfirmationDialog()
            }
    }

    private fun submitSelections() {
        InViewScope {
            if (vm.selectedInterests.flow.first().isEmpty())
                return@InViewScope Toast.makeText(requireContext(), "Pilih topik minimal 1", Toast.LENGTH_SHORT).show()


            vm.submitSelectionProvider.update(
                UpdateUserInterestPLD(
                    vm.selectedInterests.flow.first()
                        .map { it.parent }
                )
            )
        }
    }

    private fun updateLocalUserProfile() {
        InViewScope {
            vm.userProfileProvider.update(
                GetUserProfilePLD()
            )
        }
    }
}