package com.alikizilcan.stingyapp.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.alikizilcan.stingyapp.databinding.FragmentAddTransactionBinding
import com.alikizilcan.stingyapp.infra.base.BaseFragment
import com.alikizilcan.stingyapp.infra.di.TYPE
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTransactionFragment : BaseFragment() {

    private var _binding: FragmentAddTransactionBinding? = null
    private val binding get() = _binding!!

    override val viewModel: AddTransactionViewModel by viewModels()

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
        binding.selectDateButton.setOnClickListener {
            viewModel.setupDate(requireContext())
        }

        binding.saveButton.setOnClickListener {
            if(viewModel.name.value != null
                && viewModel.amount.value != null
                && viewModel.date.value != null
                && viewModel.category.value != null
                && viewModel.type.value != null
                && (viewModel.isVisibleView.value == false || viewModel.installmentCount.value != null)){
                viewModel.addTransaction(
                    navDirections = AddTransactionFragmentDirections.actionAddTransactionFragmentToTransactionsFragment()
                )
            } else {
                val snack = Snackbar.make(requireView(), "Fill All Blanks", Snackbar.LENGTH_LONG)
                snack.show()
            }
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
}