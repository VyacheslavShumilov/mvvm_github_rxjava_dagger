package com.rxjava.githubmvvmrxjavakoin.ui.users

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rxjava.githubmvvmrxjavakoin.domain.entities.UsersEntity

class UsersAdapter(private val onItemClickListener: (UsersEntity) -> Unit) :
    RecyclerView.Adapter<UsersViewHolder>() {

    private val data = mutableListOf<UsersEntity>()

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int) = getItem(position).id

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UsersViewHolder(parent, onItemClickListener)

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount() = data.size

    private fun getItem(pos: Int) = data[pos]


    @SuppressLint("NotifyDataSetChanged")
    fun setData(users: List<UsersEntity>) {
        data.clear()            //предыдущие данные исключаются
        data.addAll(users)      //добавление новых данных
        notifyDataSetChanged()  //уведомление о изменении данных
    }
}