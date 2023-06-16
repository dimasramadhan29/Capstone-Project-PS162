package com.c23_ps162.trade_net.util

import android.content.Context
import arrow.core.Either
import com.c23_ps162.trade_net.data.model.AuthToken
import com.c23_ps162.trade_net.data.model.Interest
import com.c23_ps162.trade_net.data.model.LoginResult
import com.c23_ps162.trade_net.data.model.UserProfile

class DummyBackend(
    private val context: Context
) {
    companion object {
        const val PROFILE_PREFERENCE_NAME = "dummyBackend_Profile"
        const val USER_INTERESTS = "userInterest"
    }

    private fun profilePreference() =
        context.getSharedPreferences(PROFILE_PREFERENCE_NAME, Context.MODE_PRIVATE)

    suspend fun getUserProfile() =
        UserProfile(
            userId = "userId",
            userName = "User Name",
            userEmail = "asdadno@gmail.com",
            userPhone = "+6211023031023",
            userInterest = getUserInterest()
        )

    suspend fun updateUserInterest(
        list: List<Interest>
    ): Either<ErrorMessage, Unit> {
        profilePreference().edit()
            .putStringSet(USER_INTERESTS, list.map { it.id }.toSet())
            .apply()

        return Either.Right(Unit)
    }

    suspend fun getUserInterest(): List<ID> =
        profilePreference()
            .getStringSet(USER_INTERESTS, null)
            ?.toList() ?: listOf()



    /** Lookup **/
    suspend fun getInterestList(): Either<ErrorMessage, List<Interest>> {
        return Either.Right(
            (1..10).map {
                Interest(
                    id = "$it",
                    name = "Interest $it",
                    imageUrl = sampleImageUrl.random()
                )
            }
        )
    }

    /** auth **/
    suspend fun login(): LoginResult {

        /** dummy auth **/
        return LoginResult(
            token = AuthToken(
                token = "dummy token"
            ),
            userProfile = getUserProfile()
        )
    }

    suspend fun registration() : Unit {
        return Unit
    }
}