package com.rxjava.githubmvvmrxjavakoin.ui.profile

import com.rxjava.githubmvvmrxjavakoin.domain.entities.ProfileEntity
import com.rxjava.githubmvvmrxjavakoin.domain.entities.UsersEntity
import io.reactivex.rxjava3.core.Observable

interface ProfileContract {
    interface ViewModel {

        val profileLiveData: Observable<ProfileEntity>
        val errorLiveData: Observable<Throwable>
        val progressLiveData: Observable<Boolean>

        fun onRefresh()     //управляющий метод

    }
}