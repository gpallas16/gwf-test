package com.exampl.gwftest.framework.db.prefs

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import com.exampl.core.data.source.token.TokenDataSource
import javax.inject.Inject


const val ACCESS_TOKEN = "access_token"
const val REFRESH_TOKEN = "refresh_token"

class PrefsTokenDataSource @Inject constructor(
    private val application: Application
) : TokenDataSource {

    override fun setAccessToken(accessToken: String?) {
        getPrefs().edit().putString(ACCESS_TOKEN, accessToken).apply()
    }

    override fun setRefreshToken(refreshToken: String?) =
        getPrefs().edit().putString(REFRESH_TOKEN, refreshToken).apply()

    override fun getAccessToken(): String? = getPrefs().getString(ACCESS_TOKEN, null)

    override fun getRefreshToken(): String? = getPrefs().getString(REFRESH_TOKEN, null)

    private fun getPrefs(): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(application)

}