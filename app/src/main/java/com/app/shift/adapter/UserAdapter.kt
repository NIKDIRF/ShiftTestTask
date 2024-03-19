package com.app.shift.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.shift.R
import com.app.shift.UserProfileActivity
import com.app.shift.databinding.UserItemBinding.bind
import com.app.shift.db.UserEntity
import com.squareup.picasso.Picasso

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserHolder>() {

    private var userList: List<UserEntity> = listOf()

    class UserHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = bind(item)

        fun bind(user: UserEntity) = with(binding) {
            Picasso.get().load(user.pictureLarge).into(binding.avatar)
            var text = "${user.title} ${user.firstName} ${user.lastName}"
            binding.fio.text = text
            text = "${user.streetName} ${user.streetNumber}"
            binding.address.text = text
            binding.phone.text = user.phone
            itemView.setOnClickListener {
                val userProfileIntent = Intent(it.context, UserProfileActivity::class.java)
                userProfileIntent.putExtra("user_id", user.id)
                startActivity(it.context, userProfileIntent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(userList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refresh(list: List<UserEntity>) {
        userList = list
        notifyDataSetChanged()
    }
}