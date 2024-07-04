package com.azimjonc.projects.lookmart.data.api.auth.dto

import com.azimjonc.projects.lookmart.domain.model.User
import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("username")
    val username: String
) {
    fun toUser() = User(
        username = username
    )
}
