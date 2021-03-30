package com.exampl.gwftest.framework.db.prefs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.exampl.core.data.repository.TokenRepository
import com.exampl.core.data.source.token.TokenDataSource

class TokenRepositoryExt(tokenDataSource: TokenDataSource) : TokenRepository(tokenDataSource) {

    private val accessMutableData: MutableLiveData<String?> = MutableLiveData()
    val accessTokenData: LiveData<String?>
        get() = accessMutableData

    init {
        accessMutableData.postValue(getAccessToken())
    }

    override fun refreshToken() {
        accessMutableData.postValue(getAccessToken())
    }

}