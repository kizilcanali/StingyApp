package com.alikizilcan.stingyapp.ui.transactions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.alikizilcan.stingyapp.data.model.InstallmentsEntity
import com.alikizilcan.stingyapp.databinding.ItemTransactionListBinding
import com.alikizilcan.stingyapp.domain.model.Transaction
import javax.inject.Inject

// NOTIFY DATA SET CHANGED
//

class TransactionsAdapter @Inject constructor(
    private val transactionsList: List<Transaction>,
    private val nestedRecyclerViewList: List<InstallmentsEntity>
) :
    RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder>() {

    var itemDeleteClickListener: (Transaction) -> Unit = {}

    class TransactionViewHolder(
        private val binding: ItemTransactionListBinding,
        private var itemDeleteClickListener: (Transaction) -> Unit,
        ) :
        RecyclerView.ViewHolder(binding.root) {
        val installmentsRecyclerView = binding.installmentsRecyclerView


        fun bind(transaction: Transaction) {
            var isExpanded = false
            binding.baseModel = transaction
            binding.deleteTransactionButton.setOnClickListener { itemDeleteClickListener(transaction) }
            binding.root.setOnClickListener {
                isExpanded = !isExpanded
                println(isExpanded)
                binding.installmentsRecyclerView.isVisible = isExpanded
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding =
            ItemTransactionListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding, itemDeleteClickListener)
    }
    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(transactionsList[position])
        val installmentsAdapter = InstallmentsAdapter(nestedRecyclerViewList)
        holder.installmentsRecyclerView.adapter = installmentsAdapter

    }
    override fun getItemCount(): Int {
        return transactionsList.size
    }

}