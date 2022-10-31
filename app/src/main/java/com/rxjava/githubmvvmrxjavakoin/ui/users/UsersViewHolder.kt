package com.rxjava.githubmvvmrxjavakoin.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rxjava.githubmvvmrxjavakoin.R
import com.rxjava.githubmvvmrxjavakoin.databinding.ItemUserBinding
import com.rxjava.githubmvvmrxjavakoin.domain.entities.UsersEntity

class UsersViewHolder (parent: ViewGroup, private val onItemClickListener: (usersEntity: UsersEntity) -> Unit) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)) {
    private lateinit var usersEntity: UsersEntity


    private val binding = ItemUserBinding.bind(itemView).apply {
        //вместо root можно сделать клик по аватарке "avatarImageView.setOn..."
        root.setOnClickListener {
            //вызываю callback который передан в конструкторе при создании UsersViewHolder
            onItemClickListener.invoke(usersEntity) // 3) воспользовался объектом при нажатии
        }
    }

    fun bind(usersEntity: UsersEntity) {
        this.usersEntity = usersEntity
        binding.avatarImageView.load(usersEntity.avatarUrl)
        binding.loginTextView.text = usersEntity.login
        binding.uidTextView.text = usersEntity.id.toString()
    }
}