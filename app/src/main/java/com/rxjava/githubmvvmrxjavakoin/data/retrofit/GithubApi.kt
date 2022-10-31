package com.rxjava.githubmvvmrxjavakoin.data.retrofit

import com.rxjava.githubmvvmrxjavakoin.domain.entities.UsersEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface GithubApi {
    @GET("users")
    fun getUsers(): Single<List<UsersEntity>>
}