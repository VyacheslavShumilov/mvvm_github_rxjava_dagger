package com.rxjava.githubmvvmrxjavakoin.domain.repos

import com.rxjava.githubmvvmrxjavakoin.domain.entities.ProfileEntity
import io.reactivex.rxjava3.core.Single

interface ProfileRepo {
    fun getProfile(onSuccess: (ProfileEntity) -> Unit, onError: ((Throwable) -> Unit)? = null)

    fun getProfile(): Single<ProfileEntity>
}