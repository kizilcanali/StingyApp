package com.alikizilcan.stingyapp.ui.transactions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alikizilcan.stingyapp.databinding.ItemInstallmentsListBinding
import com.alikizilcan.stingyapp.domain.model.Installments

class InstallmentsAdapter(private var installmentsList: List<Installments>) :
    RecyclerView.Adapter<InstallmentsAdapter.InstallmentsViewHolder>() {

    var itemClickListener: (Installments) -> Unit = {}

    class InstallmentsViewHolder(
        private val binding: ItemInstallmentsListBinding,
        private var itemClickListener: (Installments) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(installments: Installments) {
            binding.baseModel = installments
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                itemClickListener(installments)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstallmentsViewHolder {
        val binding =
            ItemInstallmentsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InstallmentsViewHolder(
            binding,
            itemClickListener,
        )
    }

    override fun onBindViewHolder(holder: InstallmentsViewHolder, position: Int) {
        holder.bind(installmentsList[position])
    }

    override fun getItemCount(): Int {
        return installmentsList.size
    }

}