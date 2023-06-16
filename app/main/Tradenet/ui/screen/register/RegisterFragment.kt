package com.c23_ps162.trade_net.ui.screen.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.c23_ps162.trade_net.data.payload.RegistrationPLD
import com.c23_ps162.trade_net.databinding.FragmentRegisterBinding
import com.c23_ps162.trade_net.util.IOJob
import com.c23_ps162.trade_net.util.InViewScope
import com.c23_ps162.trade_net.util.collect
import com.c23_ps162.trade_net.util.pattern.Bootstrap
import kotlinx.coroutines.flow.first
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment(), Bootstrap {
    private lateinit var binding: FragmentRegisterBinding
    private val vm: RegisterViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRegisterBinding.inflate(
            inflater,
            container,
            false
        )

        initState()
        return binding.root
    }

    override fun initState() {
        initForm()
    }

    private fun initForm() {
        /** username **/
        binding.edUname.addTextChangedListener {
            vm.userName.set(it?.toString() ?: "")
        }

        /** email **/
        run {
            binding.edEmail.addTextChangedListener {
                vm.email.set(it?.toString() ?: "")
            }

            collect(vm.emailError) {
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

        /** phone **/
        run {
            binding.edPhone.addTextChangedListener {
                vm.phoneNumber.set(it?.toString() ?: "")
            }

            collect(vm.phoneNumberError) {
                binding.tlPhone.error = when {
                    it.first && binding.edPhone.text?.isBlank() == true -> null
                    it.first && it.second.isNotBlank() -> it.second
                    else -> null
                }
                binding.tlPhone.isErrorEnabled = when {
                    it.first && binding.edPhone.text?.isBlank() == true -> false
                    it.first && it.second.isNotBlank() -> true
                    else -> false
                }
            }
        }

        /** password **/
        run {
            binding.edPassword.addTextChangedListener {
                vm.password.set(it?.toString() ?: "")
            }

            collect(vm.passwordError) {
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

        /** button confirm **/
        run {
            binding.btnConfirm.setOnClickListener {
                IOJob {
                    proceedRegistration()
                }
            }

            collect(vm.registrationProvider.success) {
                if (it.first) {
                    InViewScope {
                        Toast.makeText(
                            requireContext(),
                            "Registrasi berhasil silahkan login untuk melanjutkan",
                            Toast.LENGTH_LONG
                        ).show()

                        findNavController().popBackStack()
                    }
                }
            }
        }
    }

    private suspend fun proceedRegistration() {
        val emailIsNotValid = vm.emailError.first().first
        val passwordIsNotValid = vm.passwordError.first().first
        val phoneIsNotValid = vm.phoneNumberError.first().first
        val userNameNotValid = vm.userName.flow.first().isBlank()

        if (emailIsNotValid || passwordIsNotValid || phoneIsNotValid || userNameNotValid) {
            InViewScope {
                Toast.makeText(requireContext(), "Cek kembali data user.", Toast.LENGTH_SHORT)
                    .show()
            }
            return
        }

        /** do registration **/
        vm.registrationProvider.update(
            RegistrationPLD(
                userName = vm.userName.flow.first(),
                userEmail = vm.email.flow.first(),
                password = vm.password.flow.first(),
                phoneNumber = vm.phoneNumber.flow.first()
            )
        )
    }
}