package com.rxjava.githubmvvmrxjavakoin.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.rxjava.githubmvvmrxjavakoin.databinding.ActivityProfileBinding
import com.rxjava.githubmvvmrxjavakoin.domain.entities.ProfileEntity
import io.reactivex.rxjava3.disposables.CompositeDisposable

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val viewModel: ProfileViewModel by viewModel()
    private val viewModelDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModelDisposable.addAll(
            viewModel.profileLiveData.subscribe{showProfile(it)}
        )
    }

    private fun showProfile(profileEntity: ProfileEntity) {
        binding.loginUserTxtView.text = profileEntity.login
        binding.userIdTxtView.text = profileEntity.id.toString()

    }


}