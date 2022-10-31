package com.rxjava.githubmvvmrxjavakoin.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.rxjava.githubmvvmrxjavakoin.data.retrofit.LIST_USERS_FROM_USER
import com.rxjava.githubmvvmrxjavakoin.databinding.ActivityProfileBinding
import com.rxjava.githubmvvmrxjavakoin.domain.entities.UsersEntity
class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getParcelableExtra<UsersEntity>(LIST_USERS_FROM_USER)?.let {
            initViews(it)
        }
    }

    private fun initViews(it: UsersEntity) {
        with(binding) {
            avatarUserImage.load(it.avatarUrl)
            loginUserTxtView.text = it.login
            userIdTxtView.text = it.id.toString()
        }
    }
}