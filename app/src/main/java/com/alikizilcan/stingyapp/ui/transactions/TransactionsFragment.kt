package com.alikizilcan.stingyapp.ui.transactions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.alikizilcan.stingyapp.databinding.FragmentTransactionsBinding
import com.alikizilcan.stingyapp.infra.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/*
TODO (Bugs & New Features):
    - Error situations []
    - isPaid operation []
    - Home page pie chart []
    - UI Re-make []
*/

@AndroidEntryPoint
class TransactionsFragment : BaseFragment() {

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

        transactionsAdapter.itemCheckBoxListener = viewModel.itemCheckBoxListener

        binding.addTransactionButton.setOnClickListener {
            viewModel.addTransaction(TransactionsFragmentDirections.actionTransactionsFragmentToAddTransactionFragment())
        }
    }
}