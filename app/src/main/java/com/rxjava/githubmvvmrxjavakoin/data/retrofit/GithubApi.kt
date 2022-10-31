package com.rxjava.githubmvvmrxjavakoin.data.retrofit

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("users")
    fun getUsers(): Single<List<UsersEntityDto>>

    @GET("users/{login}")
    fun getProfile(): Single<ProfileEntityDto>
}