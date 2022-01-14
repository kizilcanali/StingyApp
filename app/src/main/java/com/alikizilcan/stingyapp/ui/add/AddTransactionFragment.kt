package com.alikizilcan.stingyapp.ui.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alikizilcan.stingyapp.databinding.FragmentAddTransactionBinding
import com.alikizilcan.stingyapp.infra.di.TYPE
import com.alikizilcan.stingyapp.infra.navigation.NavigationObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTransactionFragment : Fragment() {

    private var _binding: FragmentAddTransactionBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddTransactionViewModel by viewModels()
    private val navigationObserver = NavigationObserver()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddTransactionBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        setupWm()
        return binding.root
    }

    private fun setupWm() {
        binding.transactionDateInputButton.setOnClickListener {
            viewModel.setupDate(requireContext())
        }
        binding.saveButton.setOnClickListener {
            viewModel.addTransaction(
                navDirections = AddTransactionFragmentDirections.actionAddTransactionFragmentToTransactionsFragment()
            )
        }
    }

    override fun onResume() {
        super.onResume()

        val dropdownAdapter = DropdownItemAdapter(requireContext(), viewModel.transactionCategories)
        binding.categoryList.setAdapter(dropdownAdapter)

        val dropdownTypeAdapter =
            DropdownTypeAdapter(requireContext(), listOf(TYPE.INCOME, TYPE.EXPENSE))
        binding.typeList.setAdapter(dropdownTypeAdapter)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationObserver.observeNavigation(
            viewModel.navigation,
            findNavController(),
            viewLifecycleOwner
        )
    }
}