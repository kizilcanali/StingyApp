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
import com.alikizilcan.stingyapp.infra.Categories
import com.alikizilcan.stingyapp.infra.Category
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

            binding.categoryIcon.setImageResource(setTransactionIcon(transaction.category!!).icon!!)
            //binding.transactionCard.setCardBackgroundColor(Color.parseColor(setTransactionIcon(transaction.category!!).color))
            binding.transactionAmount.setTextColor(Color.parseColor(setAmountColor(transaction.transactionType!!)))
            binding.executePendingBindings()

            binding.deleteTransactionButton.setOnClickListener { itemDeleteClickListener(transaction) }
            binding.root.setOnClickListener {
                isExpanded = !isExpanded
                binding.installmentsRecyclerView.isVisible = isExpanded
            }
        }

        private fun setTransactionIcon(iconText: String): Category {
            return when (iconText.replace("\\s".toRegex(), "").uppercase()) {
                Categories.FUEL.name -> Category("Fuel", R.drawable.ic_fuel, "#2EB086")
                Categories.JEWELRY.name -> Category("Jewelry", R.drawable.ic_diamond, "#F6B8B8")
                Categories.TECHNOLOGY.name -> Category("Tech", R.drawable.ic_tech, "#AC66CC")
                Categories.FOODANDBEVERAGE.name -> Category("Food and Beverage", R.drawable.ic_food, "#456268")
                Categories.GROCERIES.name -> Category("Groceries", R.drawable.ic_grocery, "#464E2E")
                Categories.HEALTH.name -> Category("Health", R.drawable.ic_health, "#ACB992")
                Categories.CLOTHING.name -> Category("Clothing", R.drawable.ic_cloth, "#AC66CC")
                Categories.SPORT.name -> Category("Sport", R.drawable.ic_sport, "#FE7E6D")
                Categories.BILL.name -> Category("Bill", R.drawable.ic_bill, "#008E89")
                Categories.PET.name -> Category("Pet", R.drawable.ic_pet, "#CC9544")
                Categories.OTHERS.name -> Category("Others", R.drawable.ic_transportation, "#93B5C6")
                Categories.GIFT.name -> Category("Gift", R.drawable.ic_gift, "#00B4D8")
                Categories.HOUSE.name -> Category("House", R.drawable.ic_house, "#595260")
                Categories.CAR.name -> Category("Car", R.drawable.ic_car, "#C3B091")
                else -> Category("", 0, "#CCD1E4")
            }
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