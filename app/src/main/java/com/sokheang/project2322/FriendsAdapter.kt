package com.sokheang.project2322

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sokheang.project2322.databinding.ViewholderFriendsBinding
import com.sokheang.project2322.domain.Friend

class FriendsAdapter(private val list: ArrayList<Friend>) :
    RecyclerView.Adapter<FriendsAdapter.ViewHolder>() {

    class ViewHolder(val binding: ViewholderFriendsBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderFriendsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(list[position].imageUrl)
            .error(R.drawable.profile)
            .into(holder.binding.ivFriendProfile)
    }
}