package com.exampl.gwftest.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exampl.core.data.repository.TokenRepository
import com.exampl.core.interactor.measurement.SetTokens
import com.exampl.gwftest.framework.db.prefs.TokenRepositoryExt
import com.exampl.gwftest.framework.network.ApiService
import com.exampl.gwftest.framework.network.request.LoginRequest
import com.exampl.gwftest.framework.network.response.TokenResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val tokenRepositoryExt: TokenRepository,
    private val apiService: ApiService,
    private val setTokens: SetTokens
) : ViewModel() {

    enum class Error {
        LOGIN_FAILED
    }

    private val errorMData: MutableLiveData<Error> = MutableLiveData()
    val errorData: LiveData<Error>
        get() = errorMData

    val loadingData : MutableLiveData<Boolean> = MutableLiveData()

    init {
        loadingData.value = false
    }

    fun accessTokenData(): LiveData<String?> = (tokenRepositoryExt as TokenRepositoryExt).accessTokenData

    fun loginUser(username: String, password: String) {
        loadingData.value = true
        viewModelScope.launch {
            try {
                errorMData.postValue(null)
                val token = apiService.login(LoginRequest(username, password))
                setTokens.run(
                    SetTokens.Token(
                        token.access,
                        token.refresh
                    )
                )
            } catch (e: Exception) {
                errorMData.postValue(Error.LOGIN_FAILED)
            }
            loadingData.postValue(false)
        }
    }

}