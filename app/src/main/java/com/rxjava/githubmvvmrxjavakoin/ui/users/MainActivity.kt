package com.rxjava.githubmvvmrxjavakoin.ui.users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.rxjava.githubmvvmrxjavakoin.app
import com.rxjava.githubmvvmrxjavakoin.databinding.ActivityMainBinding
import com.rxjava.githubmvvmrxjavakoin.domain.entities.UsersEntity
import com.rxjava.githubmvvmrxjavakoin.domain.repos.UsersRepo
import com.rxjava.githubmvvmrxjavakoin.ui.profile.ProfileActivity
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: UsersAdapter

    @Inject
    lateinit var usersRepo: UsersRepo

    private val viewModel: UsersViewModel by lazy { UsersViewModel(usersRepo) }

    private val viewModelDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        app.appComponent.inject(this) // вызывается один раз, чтобы проставились значения


        initViews()

        viewModelDisposable.addAll(
            viewModel.progressObservable.subscribe{ showProgress(it) },
            viewModel.usersObservable.subscribe{ showUsers(it) },
            viewModel.errorObservable.subscribe{ showError(it) }
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

    private fun openProfileScreen(user: UsersEntity) {
        val bundle = Bundle()
        bundle.putParcelable("profile", user)

        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
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
        adapter = UsersAdapter {
            openProfileScreen(it)
        }
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.usersRecyclerView.adapter = adapter
    }
}