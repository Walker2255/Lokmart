package com.azimjonc.projects.lookmart.data.repo

import com.azimjonc.projects.lookmart.data.api.auth.AuthApi
import com.azimjonc.projects.lookmart.data.api.auth.dto.SignInRequest
import com.azimjonc.projects.lookmart.data.store.TokenStore
import com.azimjonc.projects.lookmart.data.store.UserStore
import com.azimjonc.projects.lookmart.domain.model.User
import com.azimjonc.projects.lookmart.domain.repo.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val tokenStore: TokenStore,
    private val userStore: UserStore
) : AuthRepository {

    override suspend fun signIn(username: String, password: String): User {

        val request = SignInRequest(username, password)
        val response = authApi.signIn(request)
        tokenStore.set(response.token)
        userStore.set(response.user)
        return response.user.toUser()

    }
}