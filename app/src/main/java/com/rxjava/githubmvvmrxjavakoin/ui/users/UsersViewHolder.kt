package com.rxjava.githubmvvmrxjavakoin.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rxjava.githubmvvmrxjavakoin.R
import com.rxjava.githubmvvmrxjavakoin.databinding.ItemUserBinding
import com.rxjava.githubmvvmrxjavakoin.domain.entities.UsersEntity

class UsersViewHolder (parent: ViewGroup, private val clickAtPosition: (Int) -> Unit) :
    RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
    ) {
    private val binding = ItemUserBinding.bind(itemView)


    init {
        itemView.setOnClickListener{
            clickAtPosition(adapterPosition)
        }
    }


    fun bind(usersEntity: UsersEntity) {
        with(binding) {
            avatarImageView.load(usersEntity.avatarUrl)
            loginTextView.text = usersEntity.login
            uidTextView.text = usersEntity.id.toString()
        }
    }
}