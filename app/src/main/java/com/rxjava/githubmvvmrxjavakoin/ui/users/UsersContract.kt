package com.rxjava.githubmvvmrxjavakoin.ui.users

import com.rxjava.githubmvvmrxjavakoin.domain.entities.UsersEntity
import io.reactivex.rxjava3.core.Observable

interface UsersContract {

    interface ViewModel {

        val usersObservable: Observable<List<UsersEntity>>
        val errorObservable: Observable<Throwable>
        val progressObservable: Observable<Boolean>

        fun onRefresh()     //управляющий метод

    }

}