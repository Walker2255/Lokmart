package com.azimjonc.projects.lookmart.domain.repo

import com.azimjonc.projects.lookmart.domain.model.User

interface AuthRepository {
    suspend fun signIn(username: String, password: String): User
}