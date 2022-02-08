package com.alikizilcan.stingyapp.ui.transactions

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.SurfaceControl
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alikizilcan.stingyapp.databinding.FragmentTransactionsBinding
import com.alikizilcan.stingyapp.infra.navigation.NavigationObserver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TransactionsFragment : Fragment() {

    private val navigationObserver = NavigationObserver()

    private lateinit var _binding: FragmentTransactionsBinding
    private val binding get() = _binding

    private val viewModel: TransactionsViewModel by viewModels()

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
        navigationObserver.observeNavigation(
            viewModel.navigation,
            findNavController(),
            viewLifecycleOwner
        )

        viewModel.transactionsList.observe(viewLifecycleOwner) { transactions ->

            viewModel.installmentsList.observe(viewLifecycleOwner) { installments ->
                val xAdapter = TransactionsAdapter(transactions, installments)
                transactions.let {
                    binding.transactionsRecyclerView.adapter = xAdapter
                    xAdapter.itemDeleteClickListener = viewModel.itemDeleteClickListener
                }
            }
        }

        binding.addTransactionButton.setOnClickListener {
            viewModel.addTransaction(TransactionsFragmentDirections.actionTransactionsFragmentToAddTransactionFragment())
        }
    }
}