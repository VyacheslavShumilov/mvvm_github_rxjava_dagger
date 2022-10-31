package com.rxjava.githubmvvmrxjavakoin.di

import com.rxjava.githubmvvmrxjavakoin.ui.profile.ProfileActivity
import com.rxjava.githubmvvmrxjavakoin.ui.users.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton      //если в Module есть хоть один @Singleton, то @Component тоже @Singleton (в единственном эксземпляре)
@Component(modules = [AppModule::class]) //перечисление всех модулей через запятую

interface AppComponent {
    fun inject(mainActivity: MainActivity) //создается на каждую активити

}