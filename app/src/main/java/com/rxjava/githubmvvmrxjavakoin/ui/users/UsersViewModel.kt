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

    override val usersObservable: Observable<List<UsersEntity>> = BehaviorSubject.create()
    override val errorObservable: Observable<Throwable> = BehaviorSubject.create()
    override val progressObservable: Observable<Boolean> = BehaviorSubject.create()


    override fun onRefresh() {
        loadData()
    }

    private fun loadData() {
        progressObservable.mutable().onNext(true)
        usersRepo.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    progressObservable.mutable().onNext(false)
                    usersObservable.mutable().onNext(it)
                },
                onError = {
                    progressObservable.mutable().onNext(false)
                    errorObservable.mutable().onNext(it)
                }
            )
    }


    private fun <T : Any> Observable<T>.mutable(): Subject<T> {
        return this as? Subject<T>
            ?: throw IllegalStateException("It is not MutableLiveData")
    }
}