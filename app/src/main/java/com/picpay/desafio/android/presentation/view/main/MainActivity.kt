package com.picpay.desafio.android.presentation.view.main

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.presentation.base.BaseActivity
import com.picpay.desafio.android.presentation.extension.observeNotHandled
import com.picpay.desafio.android.presentation.util.NoInternetMessage
import com.picpay.desafio.android.presentation.util.UnknownErrorMessage
import com.picpay.desafio.android.presentation.view.main.adapter.UserListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity :
    BaseActivity<ActivityMainBinding>(
        ActivityMainBinding::inflate
    ) {

    private val viewModel by viewModel<MainViewModel>()

    private lateinit var adapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
        observeViewModel()

        viewModel.refreshUsers()

    }

    private fun initViews() = with(binding) {

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

    }

    private fun observeViewModel() {

        viewModel.modelData.observe(this) {
            adapter.users = it
        }

        viewModel.loading.observe(this) {
            binding.userListProgressBar.isVisible = it
        }

        viewModel.error.observeNotHandled(this) { error ->
            when (error) {
                UserListError.NO_INTERNET -> show(NoInternetMessage)
                UserListError.UNKNOWN -> show(UnknownErrorMessage)
            }
        }

    }
}
