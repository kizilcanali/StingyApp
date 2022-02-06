package com.alikizilcan.stingyapp.ui.transactions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alikizilcan.stingyapp.databinding.ItemInstallmentsListBinding
import com.alikizilcan.stingyapp.domain.model.Installments


class InstallmentsAdapter :
    ListAdapter<Installments, InstallmentsAdapter.InstallmentsViewHolder>(DIFF_CALLBACK) {

    var itemClickListener: (Installments) -> Unit = {}

    class InstallmentsViewHolder(
        private val binding: ItemInstallmentsListBinding,
        private var itemClickListener: (Installments) -> Unit
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
        return InstallmentsViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: InstallmentsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Installments>() {
            override fun areItemsTheSame(
                oldItem: Installments,
                newItem: Installments
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Installments,
                newItem: Installments
            ): Boolean =
                oldItem == newItem

        }
    }
}