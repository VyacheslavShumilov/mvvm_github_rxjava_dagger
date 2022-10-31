package com.rxjava.githubmvvmrxjavakoin.data.retrofit

import com.google.gson.annotations.SerializedName
import com.rxjava.githubmvvmrxjavakoin.domain.entities.ProfileEntity
import com.rxjava.githubmvvmrxjavakoin.domain.entities.UsersEntity

data class UsersEntityDto(
    val login: String,
    val id: Long,
    @SerializedName("avatar_url")
    val avatarUrl: String
) {

    fun toUsersEntity() = UsersEntity(login, id, avatarUrl)
}

data class ProfileEntityDto(
    val login: String,
    val id: Long,
    @SerializedName("avatar_url")
    val avatarUrl: String
) {

    fun toProfileEntity() = ProfileEntity(login, id, avatarUrl)
}
