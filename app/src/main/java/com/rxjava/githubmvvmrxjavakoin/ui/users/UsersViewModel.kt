package com.rxjava.githubmvvmrxjavakoin.ui.users

import androidx.lifecycle.ViewModel
import com.rxjava.githubmvvmrxjavakoin.domain.entities.UsersEntity
import com.rxjava.githubmvvmrxjavakoin.domain.repos.UsersRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject

class UsersViewModel(
    private val usersRepo: UsersRepo
) : UsersContract.ViewModel, ViewModel() {

    override val usersLiveData: Observable<List<UsersEntity>> = BehaviorSubject.create()
    override val errorLiveData: Observable<Throwable> = BehaviorSubject.create()
    override val progressLiveData: Observable<Boolean> = BehaviorSubject.create()
    override val openProfileLiveData: Observable<Unit> = BehaviorSubject.create()

    override fun onRefresh() {
        loadData()
    }

    override fun onUserClick(usersEntity: UsersEntity) {
        openProfileLiveData
    }

    private fun loadData() {
        progressLiveData.mutable().onNext(true)
        usersRepo.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    progressLiveData.mutable().onNext(false)
                    usersLiveData.mutable().onNext(it)
                },
                onError = {
                    progressLiveData.mutable().onNext(false)
                    errorLiveData.mutable().onNext(it)
                }
            )
    }


    private fun <T : Any> Observable<T>.mutable(): Subject<T> {
        return this as? Subject<T>
            ?: throw IllegalStateException("It is not MutableLiveData")
    }
}