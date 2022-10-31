package com.rxjava.githubmvvmrxjavakoin.ui.users

import com.rxjava.githubmvvmrxjavakoin.domain.entities.UsersEntity
import io.reactivex.rxjava3.core.Observable

interface UsersContract {

    interface ViewModel {

        val usersLiveData: Observable<List<UsersEntity>>
        val errorLiveData: Observable<Throwable>
        val progressLiveData: Observable<Boolean>
        val openProfileLiveData: Observable<Unit> // для открытия новой активити

        fun onRefresh()     //управляющий метод
        fun onUserClick(usersEntity: UsersEntity)
    }

}