package com.exampl.gwftest.di.network

import com.exampl.core.data.repository.TokenRepository
import com.exampl.gwftest.framework.network.ApiService
import com.exampl.gwftest.framework.network.RefreshTokenService
import com.exampl.gwftest.framework.network.util.AuthInterceptor
import com.exampl.gwftest.framework.network.util.TokenAuthenticator
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object RetrofitModule {

    @Singleton
    @Provides
    fun provideApiService(authenticator: Authenticator, interceptor: Interceptor): ApiService {
        val client = OkHttpClient.Builder()
            .authenticator(authenticator)
            .addInterceptor(interceptor)
            .build();

        return Retrofit.Builder()
            .baseUrl("https://test-api.gwf.ch/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideTokenService(): RefreshTokenService {
        return Retrofit.Builder()
            .baseUrl("https://test-api.gwf.ch/auth/token/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RefreshTokenService::class.java)
    }

    @Provides
    fun provideTokenAuthenticator(
        refreshTokenService: RefreshTokenService,
        tokenRepository: TokenRepository
    ): Authenticator {
        return TokenAuthenticator(refreshTokenService, tokenRepository)
    }

    @Provides
    fun provideAuthInterceptor(tokenRepository: TokenRepository): Interceptor {
        return AuthInterceptor(tokenRepository)
    }
}