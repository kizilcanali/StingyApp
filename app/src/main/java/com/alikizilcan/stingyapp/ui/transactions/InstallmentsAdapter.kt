package com.alikizilcan.stingyapp.ui.transactions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alikizilcan.stingyapp.databinding.ItemInstallmentsListBinding
import com.alikizilcan.stingyapp.domain.model.Installments
import com.alikizilcan.stingyapp.infra.toBool
import com.alikizilcan.stingyapp.infra.toInt

class InstallmentsAdapter :
    RecyclerView.Adapter<InstallmentsAdapter.InstallmentsViewHolder>() {

    var itemCheckBoxListener: (Int, Double, Installments) -> Unit = {Int, Double, Installments -> }
    var installmentsList: List<Installments> = listOf()



    class InstallmentsViewHolder(
        private val binding: ItemInstallmentsListBinding,
        private var itemCheckBoxListener: (Int, Double, Installments) -> Unit,
        ) : RecyclerView.ViewHolder(binding.root) {
        //var isBoxChecked : Boolean = false

        fun bind(installments: Installments) {
            binding.baseModel = installments

            binding.isPaidCheck.isChecked = installments.isPaid!!.toBool()
            binding.executePendingBindings()
            binding.isPaidCheck.setOnClickListener {
                itemCheckBoxListener(binding.isPaidCheck.isChecked.toInt(), binding.monthlyPayment.text.toString().toDouble(), installments)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstallmentsViewHolder {
        val binding =
            ItemInstallmentsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InstallmentsViewHolder(
            binding,
            itemCheckBoxListener,
        )
    }

    override fun onBindViewHolder(holder: InstallmentsViewHolder, position: Int) {
        holder.bind(installmentsList[position])
        //holder.isBoxChecked = installmentsList[position].isPaid!!.toBool()

    }

    override fun getItemCount(): Int {
        return installmentsList.size
    }
}