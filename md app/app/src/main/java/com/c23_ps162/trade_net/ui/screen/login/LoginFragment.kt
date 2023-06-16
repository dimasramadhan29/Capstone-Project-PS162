package com.c23_ps162.trade_net.ui.screen.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.c23_ps162.trade_net.data.payload.LoginPLD
import com.c23_ps162.trade_net.databinding.FragmentLoginBinding
import com.c23_ps162.trade_net.util.IOJob
import com.c23_ps162.trade_net.util.InViewScope
import com.c23_ps162.trade_net.util.Preference
import com.c23_ps162.trade_net.util.collect
import com.c23_ps162.trade_net.util.pattern.Bootstrap
import com.c23_ps162.trade_net.util.showExitConfirmationDialog
import kotlinx.coroutines.flow.first
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(), Bootstrap {

    private lateinit var binding: FragmentLoginBinding
    private val state: LoginViewModel by viewModel()
    private val preference by inject<Preference>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(
            inflater,
            container,
            false
        )

        initState()
        return binding.root
    }

    override fun initState() {
        setupRegisterButton()
        setupForm()
        setupBackPress()
    }

    private fun setupRegisterButton() {
        with(binding.registerBtn) {
            this.setOnClickListener {
                goToRegistration()
            }
        }
    }

    private fun setupForm() {
        /** User Name **/
        run {
            binding.edEmail.addTextChangedListener {
                state.email.set(it?.toString() ?: "")
            }

            collect(state.emailError) {
                binding.tlEmail.error = when {
                    it.first && binding.edEmail.text?.isBlank() == true -> null
                    it.first && it.second.isNotBlank() -> it.second
                    else -> null
                }
                binding.tlEmail.isErrorEnabled = when {
                    it.first && binding.edEmail.text?.isBlank() == true -> false
                    it.first && it.second.isNotBlank() -> true
                    else -> false
                }
            }
        }

        /** Password **/
        run {
            binding.edPassword.addTextChangedListener {
                state.password.set(it?.toString() ?: "")
            }

            collect(state.passwordError) {
                binding.tlPassword.error = when {
                    it.first && binding.edPassword.text?.isBlank() == true -> null
                    it.first && it.second.isNotBlank() -> it.second
                    else -> null
                }
                binding.tlPassword.isErrorEnabled = when {
                    it.first && binding.edPassword.text?.isBlank() == true -> false
                    it.first && it.second.isNotBlank() -> true
                    else -> false
                }
            }
        }

        /** Login Button **/
        run {
            /** State **/
            //            suspend fun updateButtonState() {
            //                val emailIsValid = state.emailError.first().first
            //                val passwordIsValid = state.passwordError.first().first
            //
            //                binding.loginBtn.isEnabled = emailIsValid && passwordIsValid
            //            }
            //
            //            collect(
            //                state.emailError
            //            ) {
            //                updateButtonState()
            //            }
            //
            //            collect(state.passwordError) {
            //                updateButtonState()
            //            }


            /** Behavior **/
            binding.loginBtn.setOnClickListener {
                IOJob {
                    proceedLogin()
                }
            }

            collect(state.loginProvider.success) {
                if (it.first) {
                    it.second?.token?.apply {
                        preference.saveAuthToken(
                            this
                        )
                    }

                    it.second?.userProfile?.apply {
                        preference.saveUserProfile(
                            this
                        )
                    }

                    goToHomePage()
                }
            }
        }
    }

    private fun setupBackPress() {
        activity?.onBackPressedDispatcher
            ?.addCallback(viewLifecycleOwner) {
                showExitConfirmationDialog()
            }
    }

    /** Control **/
    private fun goToRegistration() {
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        findNavController().navigate(action)
    }

    private suspend fun proceedLogin() {
        val emailIsNotValid = state.emailError.first().first
        val passwordIsNotValid = state.passwordError.first().first

        if (emailIsNotValid || passwordIsNotValid) {
            InViewScope {
                Toast.makeText(requireContext(), "Cek kembali email dan password", Toast.LENGTH_SHORT)
                    .show()
            }
            return
        }

        /** do login **/
        state.loginProvider.update(
            LoginPLD(
                email = state.email.flow.first(),
                password = state.password.flow.first()
            )
        )
    }

    private fun goToHomePage() {
        val action = LoginFragmentDirections.actionLoginFragmentToDashboardFragment()
        findNavController().navigate(action)
    }
}