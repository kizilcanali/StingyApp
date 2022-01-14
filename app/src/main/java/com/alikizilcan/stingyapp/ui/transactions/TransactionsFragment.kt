package com.alikizilcan.stingyapp.ui.transactions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alikizilcan.stingyapp.R
import com.alikizilcan.stingyapp.databinding.FragmentAddTransactionBinding
import com.alikizilcan.stingyapp.databinding.FragmentTransactionsBinding
import com.alikizilcan.stingyapp.infra.navigation.NavigationObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionsFragment : Fragment() {

    private val navigationObserver = NavigationObserver()

    private lateinit var _binding: FragmentTransactionsBinding
    private val binding get() = _binding

    private val viewModel:TransactionsViewModel by viewModels()
    private val transactionsAdapter = TransactionsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

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
        binding.transactionsRecyclerView.adapter = transactionsAdapter

        viewModel.transactionsList.observe(viewLifecycleOwner){
            transactionsAdapter.submitList(it)
        }
        transactionsAdapter.itemClickListener = viewModel.itemClickListener

        binding.addTransactionButton.setOnClickListener {
            viewModel.addTransaction(TransactionsFragmentDirections.actionTransactionsFragmentToAddTransactionFragment())
        }

    }
}