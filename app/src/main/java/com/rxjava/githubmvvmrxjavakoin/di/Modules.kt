package com.rxjava.githubmvvmrxjavakoin.di

import com.rxjava.githubmvvmrxjavakoin.data.retrofit.RetrofitUsersRepoImpl
import com.rxjava.githubmvvmrxjavakoin.data.retrofit.GithubApi
import com.rxjava.githubmvvmrxjavakoin.data.retrofit.RetrofitProfileRepoImpl
import com.rxjava.githubmvvmrxjavakoin.domain.repos.ProfileRepo
import com.rxjava.githubmvvmrxjavakoin.domain.repos.UsersRepo
import com.rxjava.githubmvvmrxjavakoin.ui.profile.ProfileViewModel
import com.rxjava.githubmvvmrxjavakoin.ui.users.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single( qualifier("url")) {"https://api.github.com/"}

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named("url")))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) //RxJava3 Adapter
            .build()
    }

    single <GithubApi> {
        get<Retrofit>().create(GithubApi::class.java)
    }

    single<UsersRepo> {
        RetrofitUsersRepoImpl(get())
    }

    single<ProfileRepo> {
        RetrofitProfileRepoImpl(get())
    }

    viewModel { UsersViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}