package com.c23_ps162.trade_net.data.repository

import arrow.core.Either
import com.c23_ps162.trade_net.data.model.UserProfile
import com.c23_ps162.trade_net.data.payload.GetUserProfilePLD
import com.c23_ps162.trade_net.data.payload.UpdateUserInterestPLD
import com.c23_ps162.trade_net.util.DummyBackend
import com.c23_ps162.trade_net.util.ErrorMessage

class ProfileRepository(
    private val dummyBackend: DummyBackend
) {
    suspend fun updateUserInterest(
        pld: UpdateUserInterestPLD
    ): Either<ErrorMessage, Unit> {
        return dummyBackend.updateUserInterest(pld.interest)
    }

    suspend fun getUserProfile(
        pld: GetUserProfilePLD
    ): Either<ErrorMessage, UserProfile> {
        return Either.Right(dummyBackend.getUserProfile())
    }
}