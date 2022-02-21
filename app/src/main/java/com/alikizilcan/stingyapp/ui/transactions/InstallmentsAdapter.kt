package com.alikizilcan.stingyapp.ui.transactions

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alikizilcan.stingyapp.databinding.ItemInstallmentsListBinding
import com.alikizilcan.stingyapp.domain.model.Installments
import com.alikizilcan.stingyapp.infra.Categories
import com.alikizilcan.stingyapp.infra.toBool
import com.alikizilcan.stingyapp.infra.toInt

class InstallmentsAdapter :
    RecyclerView.Adapter<InstallmentsAdapter.InstallmentsViewHolder>() {

    var itemCheckBoxListener: (Int, Double, Installments) -> Unit = {Int, Double, Installments -> }
    var installmentsList: List<Installments> = listOf()
    var categoryKey: String = ""

    class InstallmentsViewHolder(
        private val binding: ItemInstallmentsListBinding,
        private var itemCheckBoxListener: (Int, Double, Installments) -> Unit,
        private var categoryText: String
        ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(installments: Installments) {

            binding.baseModel = installments
            binding.isPaidCheck.isChecked = installments.isPaid!!.toBool()
            //binding.installmentLayout.setBackgroundColor( Color.parseColor(setInstallmentsBackground(categoryText)))
            binding.executePendingBindings()
            binding.isPaidCheck.setOnClickListener {
                itemCheckBoxListener(binding.isPaidCheck.isChecked.toInt(), binding.monthlyPayment.text.toString().toDouble(), installments)
            }
        }

        private fun setInstallmentsBackground(categoryText: String): String {
            return when (categoryText.uppercase()) {
                //Test Values
                Categories.FUEL.name -> "#FC4F4F"
                Categories.JEWELRY.name -> "#FC4F4F"
                Categories.TECHNOLOGY.name -> "#FF9F45"
                Categories.TRANSPORTATION.name -> "#FFBC80"
                else -> "#F76E11"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstallmentsViewHolder {
        val binding =
            ItemInstallmentsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InstallmentsViewHolder(
            binding,
            itemCheckBoxListener,
            categoryKey
        )
    }

    override fun onBindViewHolder(holder: InstallmentsViewHolder, position: Int) {
        holder.bind(installmentsList[position])

    }
    override fun getItemCount(): Int {
        return installmentsList.size
    }
}