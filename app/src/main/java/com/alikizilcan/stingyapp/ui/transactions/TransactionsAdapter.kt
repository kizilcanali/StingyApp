package com.alikizilcan.stingyapp.ui.transactions

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alikizilcan.stingyapp.R
import com.alikizilcan.stingyapp.databinding.ItemTransactionListBinding
import com.alikizilcan.stingyapp.domain.model.Installments
import com.alikizilcan.stingyapp.domain.model.Transaction
import com.alikizilcan.stingyapp.infra.CategoriesList
import javax.inject.Inject

class TransactionsAdapter @Inject constructor() :
    ListAdapter<Transaction, TransactionsAdapter.TransactionViewHolder>(DIFF_CALLBACK) {

    var itemDeleteClickListener: (Transaction) -> Unit = {}
    var itemCheckBoxListener: (Int, Double, Installments) -> Unit = { Int, Double, Installments -> }

    class TransactionViewHolder(
        private val binding: ItemTransactionListBinding,
        private var itemDeleteClickListener: (Transaction) -> Unit,

        ) :
        RecyclerView.ViewHolder(binding.root) {

        val installmentsRecyclerView = binding.installmentsRecyclerView

        fun bind(transaction: Transaction) {
            var isExpanded = false
            binding.baseModel = transaction

            if (transaction.category != null && transaction.transactionType != null) {
                binding.categoryIcon.setImageResource(setTransactionIcon(transaction.category))
                binding.transactionAmount.setTextColor(Color.parseColor(setAmountColor(transaction.transactionType)))
            }

            binding.executePendingBindings()
            binding.deleteTransactionButton.setOnClickListener { itemDeleteClickListener(transaction) }
            binding.root.setOnClickListener {
                isExpanded = !isExpanded
                binding.installmentsRecyclerView.isVisible = isExpanded
            }
        }

        private fun setTransactionIcon(iconText: String): Int {
            for (i in CategoriesList.listOfCategories) {
                if (iconText == i.name) {
                    return i.icon!!
                }
            }
            return R.drawable.ic_other
        }
        private fun setAmountColor(type: String): String {
            return when (type) {
                "Income" -> "#34C759"
                else -> "#FF3B30"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding =
            ItemTransactionListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding, itemDeleteClickListener)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(getItem(position))
        val list = getItem(position).installments
        var installmentsAdapter = InstallmentsAdapter()
        if (list != null) {
            installmentsAdapter.installmentsList = list
            holder.installmentsRecyclerView.adapter = installmentsAdapter
        }
        installmentsAdapter.itemCheckBoxListener = itemCheckBoxListener
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