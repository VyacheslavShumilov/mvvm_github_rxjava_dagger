package com.rxjava.githubmvvmrxjavakoin.ui.users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.rxjava.githubmvvmrxjavakoin.databinding.ActivityMainBinding
import com.rxjava.githubmvvmrxjavakoin.domain.entities.UsersEntity
import com.rxjava.githubmvvmrxjavakoin.ui.profile.ProfileActivity
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val adapter = UsersAdapter {
        viewModel.onUserClick(it)
    }

    private val viewModel: UsersViewModel by viewModel()

    private val viewModelDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()

        viewModelDisposable.addAll(
            viewModel.progressLiveData.subscribe{ showProgress(it) },
            viewModel.usersLiveData.subscribe{ showUsers(it) },
            viewModel.errorLiveData.subscribe{ showError(it) },
            viewModel.openProfileLiveData.subscribe{ openProfileScreen() }
        )
    }

    override fun onDestroy() {
        viewModelDisposable.dispose()
        super.onDestroy()
    }


    private fun initViews() {
        binding.refreshButton.setOnClickListener {
            viewModel.onRefresh()
        }
        initRecyclerView()

        showProgress(false)
    }

    private fun openProfileScreen() {
        startActivity(Intent(this, ProfileActivity::class.java))
    }


    private fun showUsers(users: List<UsersEntity>) {
        adapter.setData(users)
    }

    private fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun showProgress(inProgress: Boolean) {
        binding.progressBar.isVisible = inProgress
        binding.usersRecyclerView.isVisible = !inProgress
    }

    private fun initRecyclerView() {
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.usersRecyclerView.adapter = adapter
    }
}