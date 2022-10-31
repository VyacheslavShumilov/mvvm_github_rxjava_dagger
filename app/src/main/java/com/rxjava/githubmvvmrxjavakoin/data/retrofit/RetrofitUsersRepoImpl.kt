package com.rxjava.githubmvvmrxjavakoin.data.retrofit

import com.rxjava.githubmvvmrxjavakoin.domain.entities.UsersEntity
import com.rxjava.githubmvvmrxjavakoin.domain.repos.UsersRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy

class RetrofitUsersRepoImpl(private val api: GithubApi) : UsersRepo {


    override fun getUsers(onSuccess: (List<UsersEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {

        api.getUsers().subscribeBy(
            onSuccess = { users ->
                onSuccess.invoke(users.map { it.toUsersEntity() })
            },
            onError = {
                onError?.invoke(it)
            }
        )
    }

    override fun getUsers(): Single<List<UsersEntity>> = api.getUsers()
        .map { users ->
            users.map {
                it.toUsersEntity()
            }
        }
}