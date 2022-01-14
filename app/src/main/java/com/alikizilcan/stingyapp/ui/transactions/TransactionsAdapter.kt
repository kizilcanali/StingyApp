package com.alikizilcan.stingyapp.ui.transactions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alikizilcan.stingyapp.databinding.ItemTransactionListBinding
import com.alikizilcan.stingyapp.domain.model.Transaction

class TransactionsAdapter :
    ListAdapter<Transaction, TransactionsAdapter.TransactionViewHolder>(DIFF_CALLBACK) {
    var itemClickListener: (Transaction) -> Unit = {}

    class TransactionViewHolder(private val binding: ItemTransactionListBinding, private var itemClickListener:(Transaction) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: Transaction) {
            binding.baseModel = transaction
            binding.executePendingBindings()
            binding.deleteTransactionButton.setOnClickListener {
                itemClickListener(transaction)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding = ItemTransactionListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Transaction>() {
            override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean =
                oldItem == newItem

        }
    }

}