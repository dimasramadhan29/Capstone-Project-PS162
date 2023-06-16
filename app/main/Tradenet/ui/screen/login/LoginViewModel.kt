package com.c23_ps162.trade_net.ui.screen.login

import androidx.lifecycle.ViewModel
import com.c23_ps162.trade_net.data.repository.AuthRepository
import com.c23_ps162.trade_net.util.emailPattern
import com.c23_ps162.trade_net.util.provider
import com.c23_ps162.trade_net.util.state
import kotlinx.coroutines.flow.map

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    val email by state("")
    val emailError = email.flow.map {
        val isValid = it.matches(emailPattern)

        !isValid to
                if (isValid) ""
                else "Format Email Salah."
    }

    val password by state("")
    val passwordError = password.flow.map {
        val isValid = it.length >= 8

        !isValid to
                if (isValid) ""
                else "Pasword harus lebih dari 8 karakter"
    }

    val loginProvider by provider(
        operator = authRepository::login
    )
}