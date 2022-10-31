package com.rxjava.githubmvvmrxjavakoin

import android.app.Application
import android.content.Context
import com.rxjava.githubmvvmrxjavakoin.di.AppComponent
import com.rxjava.githubmvvmrxjavakoin.di.DaggerAppComponent


class App: Application() {
    val appComponent: AppComponent by lazy { DaggerAppComponent.create() }

}

val Context.app: App get() = applicationContext as App