package com.alikizilcan.stingyapp.ui.transactions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alikizilcan.stingyapp.data.model.InstallmentsEntity
import com.alikizilcan.stingyapp.databinding.ItemInstallmentsListBinding
import com.alikizilcan.stingyapp.domain.model.Installments

class InstallmentsAdapter(private val listOfInstallments: List<InstallmentsEntity>) :
    RecyclerView.Adapter<InstallmentsAdapter.InstallmentsViewHolder>() {

    var itemClickListener: (InstallmentsEntity) -> Unit = {}

    class InstallmentsViewHolder(
        private val binding: ItemInstallmentsListBinding,
        private var itemClickListener: (InstallmentsEntity) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(installments: InstallmentsEntity) {
            binding.baseModel = installments

            binding.root.setOnClickListener {
                itemClickListener(installments)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstallmentsViewHolder {
        val binding =
            ItemInstallmentsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InstallmentsViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: InstallmentsViewHolder, position: Int) {
        val item = listOfInstallments[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return listOfInstallments.size
    }
}