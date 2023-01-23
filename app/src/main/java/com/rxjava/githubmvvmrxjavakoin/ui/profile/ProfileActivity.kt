package com.rxjava.githubmvvmrxjavakoin.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import coil.load
import com.rxjava.githubmvvmrxjavakoin.databinding.ActivityProfileBinding
import com.rxjava.githubmvvmrxjavakoin.domain.entities.UsersEntity
class ProfileActivity : AppCompatActivity() {

    private val PROFILE_KEY = "profile"

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val selectedProfile = intent.getParcelableExtra<UsersEntity>(PROFILE_KEY)
        if (selectedProfile !=null) {
            initViews(selectedProfile)
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }

    }

    private fun initViews(it: UsersEntity) {
        with(binding) {
            avatarUserImage.load(it.avatarUrl)
            textView2.text = it.login
            userIdTxtView.text = it.id.toString()
        }
    }
}
