package com.azimjonc.projects.lookmart.data.api.auth.dto

import com.google.gson.annotations.SerializedName

data class SignInRequest(
    @SerializedName("username")
    val userName: String,
    @SerializedName("password")
    val password: String
)