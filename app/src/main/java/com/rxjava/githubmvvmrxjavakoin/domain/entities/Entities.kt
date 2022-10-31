package com.rxjava.githubmvvmrxjavakoin.domain.entities

data class UsersEntity(
    val login: String,
    val id: Long,
    val avatarUrl: String
)

data class ProfileEntity(
    val login: String,
    val id: Long,
    val avatarUrl: String
)