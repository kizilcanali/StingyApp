package com.alikizilcan.stingyapp.ui.transactions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alikizilcan.stingyapp.data.model.InstallmentsEntity
import com.alikizilcan.stingyapp.databinding.FragmentTransactionsBinding
import com.alikizilcan.stingyapp.domain.model.Installments
import com.alikizilcan.stingyapp.domain.model.Transaction
import com.alikizilcan.stingyapp.infra.base.BaseFragment
import com.alikizilcan.stingyapp.infra.navigation.NavigationObserver
import dagger.hilt.android.AndroidEntryPoint

/*
TODO (Bugs & New Features):
    - Error situations []
    - isPaid operation []
    - Home page pie chart []
*/

@AndroidEntryPoint
class TransactionsFragment : BaseFragment() {

    private val navigationObserver = NavigationObserver()

    private lateinit var _binding: FragmentTransactionsBinding
    private val binding get() = _binding

    override val viewModel: TransactionsViewModel by viewModels()
    private val transactionsAdapter = TransactionsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTransactionsBinding.inflate(inflater, container, false)
        _binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.transactionsRecyclerView.adapter = transactionsAdapter

        viewModel.transactionsList.observe(viewLifecycleOwner) { transactions ->
            transactionsAdapter.submitList(transactions)

            transactionsAdapter.itemDeleteClickListener = {
                viewModel.itemDeleteClickListener(it)
                transactionsAdapter.notifyDataSetChanged()
            }
        }
        binding.addTransactionButton.setOnClickListener {
            viewModel.addTransaction(TransactionsFragmentDirections.actionTransactionsFragmentToAddTransactionFragment())
        }
    }
}