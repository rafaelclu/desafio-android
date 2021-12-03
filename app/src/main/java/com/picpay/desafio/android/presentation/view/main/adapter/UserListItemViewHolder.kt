package com.picpay.desafio.android.presentation.view.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.presentation.extension.loadImageFromNetwork
import com.picpay.desafio.android.presentation.view.main.model.UserModelData

class UserListItemViewHolder(
    private val binding: ListItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: UserModelData) = with(binding) {
        name.text = user.name
        username.text = user.username
        progressBar.visibility = View.VISIBLE

        picture.loadImageFromNetwork(
            imageUrl = user.imageUrl,
            errorPlaceholder = R.drawable.ic_round_account_circle,
            onSuccess = { progressBar.visibility = View.GONE },
            onError = { progressBar.visibility = View.GONE }

        )
    }
}