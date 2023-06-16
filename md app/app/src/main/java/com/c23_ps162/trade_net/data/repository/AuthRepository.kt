package com.c23_ps162.trade_net.data.repository

import arrow.core.Either
import com.c23_ps162.trade_net.data.model.LoginResult
import com.c23_ps162.trade_net.data.payload.LoginPLD
import com.c23_ps162.trade_net.data.payload.RegistrationPLD
import com.c23_ps162.trade_net.util.DummyBackend
import com.c23_ps162.trade_net.util.ErrorMessage

class AuthRepository(
    private val dummyBackend: DummyBackend
) {
    suspend fun login(
        pld: LoginPLD
    ): Either<ErrorMessage, LoginResult> {
        return Either.Right(dummyBackend.login())
    }

    suspend fun registration(
        pld: RegistrationPLD
    ): Either<ErrorMessage, Unit> {
        return Either.Right(dummyBackend.registration())
    }
}