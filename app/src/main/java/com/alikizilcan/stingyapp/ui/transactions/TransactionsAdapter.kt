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
import com.alikizilcan.stingyapp.domain.model.Transaction
import com.alikizilcan.stingyapp.infra.Categories
import com.alikizilcan.stingyapp.infra.Category
import javax.inject.Inject

class TransactionsAdapter @Inject constructor() :
    ListAdapter<Transaction, TransactionsAdapter.TransactionViewHolder>(DIFF_CALLBACK) {

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

            binding.categoryIcon.setImageResource(setTransactionIcon(transaction.category!!).icon!!)
            binding.transactionCard.setCardBackgroundColor(
                Color.parseColor(
                    setTransactionIcon(
                        transaction.category!!
                    ).color
                )
            )
            binding.executePendingBindings()

            binding.deleteTransactionButton.setOnClickListener { itemDeleteClickListener(transaction) }
            binding.root.setOnClickListener {
                isExpanded = !isExpanded
                binding.installmentsRecyclerView.isVisible = isExpanded
            }

        }

        private fun setTransactionIcon(iconText: String): Category {
            return when (iconText.uppercase()) {
                //Test Values
                Categories.FUEL.name -> Category("Fuel", R.drawable.ic_food, "#FC4F4F")
                Categories.JEWELRY.name -> Category("Jewelry", R.drawable.ic_diamond, "#FC4F4F")
                Categories.TECHNOLOGY.name -> Category("Tech", R.drawable.ic_pet, "#FF9F45")
                Categories.TRANSPORTATION.name -> Category(
                    "Transportation",
                    R.drawable.ic_transportation,
                    "#FFBC80"
                )
                else -> Category("Fuel", R.drawable.ic_food, "#F76E11")
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
        if (list != null) {
            var installmentsAdapter = InstallmentsAdapter(list)
            holder.installmentsRecyclerView.adapter = installmentsAdapter
        }

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