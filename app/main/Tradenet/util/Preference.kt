package com.c23_ps162.trade_net.util

import android.content.Context
import com.c23_ps162.trade_net.data.model.AuthToken
import com.c23_ps162.trade_net.data.model.UserProfile

class Preference(
    private val context: Context
) {

    companion object {
        const val AUTH_PREFERENCE_NAME = "auth"
        const val PROFILE_PREFERENCE_NAME = "profile"
        const val AUTH_TOKEN = "auth_token"
        const val USER_PROFILE = "user_profile"
    }

    private fun authPreference() =
        context.getSharedPreferences(AUTH_PREFERENCE_NAME, Context.MODE_PRIVATE)

    private fun profilePreference() =
        context.getSharedPreferences(PROFILE_PREFERENCE_NAME, Context.MODE_PRIVATE)

    suspend fun saveAuthToken(
        token: AuthToken
    ) {
        authPreference()
            .edit()
            .putString(AUTH_TOKEN, token.token)
            .apply()
    }

    suspend fun getAuthToken() = authPreference()
        .getString(AUTH_TOKEN, null)

    suspend fun saveUserProfile(
        userProfile: UserProfile
    ) {
        profilePreference()
            .edit()
            .putString(USER_PROFILE, userProfile.toJsonString())
            .apply()
    }

    suspend fun getUserProfile(): UserProfile? {
        return profilePreference()
            .getString(USER_PROFILE, "{}")
            ?.toObject<UserProfile>()
    }
}

