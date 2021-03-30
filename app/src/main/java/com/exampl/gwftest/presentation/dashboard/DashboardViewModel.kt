package com.exampl.gwftest.presentation.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exampl.core.data.repository.TokenRepository
import com.exampl.gwftest.framework.db.prefs.TokenRepositoryExt
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val tokenRepository: TokenRepository,
) : ViewModel() {

    private val searchData: MutableLiveData<String?> = MutableLiveData()
    val search: LiveData<String?>
        get() = searchData

    fun accessTokenData(): LiveData<String?> =
        (tokenRepository as TokenRepositoryExt).accessTokenData

    fun signOut() {
        tokenRepository.clearAll()
    }

    fun setSearchText(text: String?) {
        searchData.postValue(text)
    }

}