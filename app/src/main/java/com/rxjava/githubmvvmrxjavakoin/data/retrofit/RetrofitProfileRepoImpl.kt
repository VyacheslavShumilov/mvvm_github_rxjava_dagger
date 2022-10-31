package com.rxjava.githubmvvmrxjavakoin.data.retrofit

import com.rxjava.githubmvvmrxjavakoin.domain.entities.ProfileEntity
import com.rxjava.githubmvvmrxjavakoin.domain.repos.ProfileRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy

class RetrofitProfileRepoImpl(private val api: GithubApi) : ProfileRepo {

    override fun getProfile(onSuccess: (ProfileEntity) -> Unit, onError: ((Throwable) -> Unit)?) {
        api.getProfile().subscribeBy(
            onSuccess = { profile ->
                onSuccess.invoke(profile.toProfileEntity())
            },
            onError = {
                onError?.invoke(it)
            }
        )
    }

    override fun getProfile(): Single<ProfileEntity> = api.getProfile()
        .map { profile ->
            profile.toProfileEntity()
        }
}