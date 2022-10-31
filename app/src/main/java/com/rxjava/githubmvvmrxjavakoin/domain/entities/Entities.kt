package com.rxjava.githubmvvmrxjavakoin.domain.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UsersEntity(
    val login: String,
    val id: Long,
    @SerializedName("avatar_url")
    val avatarUrl: String
) : Parcelable