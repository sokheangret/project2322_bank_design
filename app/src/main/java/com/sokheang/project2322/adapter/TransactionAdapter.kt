package com.sokheang.project2322.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sokheang.project2322.R
import com.sokheang.project2322.databinding.ViewholderLastTransactionBinding
import com.sokheang.project2322.domain.Transaction

/**
 * Create by Sokheang RET on 27-May-25.
 **/
class TransactionAdapter(private val transactionList: ArrayList<Transaction>) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    class ViewHolder(val binding: ViewholderLastTransactionBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderLastTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  ViewHolder(binding)
    }

    override fun getItemCount(): Int = transactionList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvTitle.text = transactionList[position].name
        holder.binding.tvDate.text = transactionList[position].date

        val amountDouble = transactionList[position].amount.toDouble()
        if(amountDouble >= 0) holder.binding.tvAmount.setTextColor(Color.GREEN)
        else holder.binding.tvAmount.setTextColor(Color.RED)

        holder.binding.tvAmount.text = "\$ $amountDouble"

        Glide.with(holder.itemView)
            .load(transactionList[position].imageUrl)
            .into(holder.binding.ivTransaction)
    }
}